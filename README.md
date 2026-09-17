# Kastoria

A Suzerain-inspired political strategy game for Android, built with Kotlin + Jetpack Compose.
You play the newly elected President of the fictional republic of Kastoria: manage a cabinet,
set national budgets, and make branching decisions through an economic crisis, unrest, a
border standoff, and a diplomatic gambit — leading to one of four endings.

All setting, characters, and text are original.

## How to build and run

1. Install **Android Studio** (Koala/2024.1 or newer) with JDK 17.
2. Open this folder (`Kastoria/`) as a project in Android Studio.
3. Let Gradle sync — this downloads dependencies, so you'll need an internet connection the
   first time.
4. Create/select an emulator running API 24+ (or plug in a physical Android device with
   USB debugging on), and hit **Run**.

There's no signing config needed to run a debug build. To produce an installable APK yourself:
`Build > Build Bundle(s) / APK(s) > Build APK(s)` in Android Studio, or `./gradlew assembleDebug`
from the project root — the APK lands in `app/build/outputs/apk/debug/`.

## How it's structured

- `model/` — plain data classes: `StoryNode`, `Choice`, `Minister`, `GameState`.
- `engine/`
  - `ContentLoader.kt` reads `story_arcs.json` and `ministers.json` from `assets/`.
  - `GameEngine.kt` filters which choices are currently valid (flag/stat gated) and applies
    a chosen choice's effects to game state.
- `viewmodel/GameViewModel.kt` — holds the current `GameState` as a `StateFlow`, exposes
  `choose()`, `updateBudget()`, `confirmBudget()`, and `restart()`, and resolves which of the
  four endings fits the player's final stats.
- `ui/` — Compose screens: `EventScreen` (dialogue/choices), `BudgetScreen` (sliders),
  `CabinetScreen` (roster), `EndingScreen`, plus `DashboardDialog`, a stat/cabinet overlay
  accessible any time via the (i) icon in the top bar.
- `assets/story_arcs.json` — the entire story graph. Every beat is a node with an `id`,
  a `type` (`dialogue` / `budget` / `cabinet` / `ending`), and a list of `choices`.
- `assets/ministers.json` — the starting cabinet roster.

## How to extend the story

Everything narrative lives in `story_arcs.json` — no code changes needed to add content:

```json
{
  "id": "new_node_id",
  "arc": "some_arc_name",
  "type": "dialogue",
  "speaker": "Someone",
  "text": "What's happening.",
  "choices": [
    {
      "text": "What the player can say/do.",
      "requiresFlag": "optional_flag_that_must_be_set",
      "forbidsFlag": "optional_flag_that_must_NOT_be_set",
      "minStat": {"stat": "economy", "value": 50},
      "effects": [{"stat": "approval", "delta": 5}],
      "setFlags": ["flag_this_sets"],
      "next": "id_of_the_node_this_leads_to"
    }
  ]
}
```

- Stats currently tracked: `economy`, `approval`, `stability`, `military`, `diplomacy`,
  `corruption` (all 0–100).
- Set a choice's `next` to `"AUTO_ENDING"` to let `GameViewModel.resolveEnding()` pick the
  ending programmatically from final stats/flags, instead of hardcoding one.
- To add a whole new ending, add a `type: "ending"` node and a branch in `resolveEnding()`.

## Known limitations / good next steps

- No save/load persistence yet (state resets on process death) — would be a good use of
  Jetpack DataStore.
- Budget sliders are independent 0–100 sliders rather than being constrained to sum to 100 —
  simpler for a first build, but worth tightening.
- No animations/transitions between screens yet.
- Cabinet is currently read-only; reassigning or firing ministers would be a natural next
  feature (the `Minister` model and `CabinetScreen` are already set up to extend easily).

 
