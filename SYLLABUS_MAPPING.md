# SYLLABUS Mapping

MODULE I
- Activities: `MainActivity`, `QuizActivity`, `ResultActivity`, `CareerListActivity`, `CareerDetailsActivity`, `SavedCareersActivity`, `SettingsActivity`, `AboutActivity`, `LoginActivity`
- Intents: Activity navigation and built-in apps (browser, email, maps)
- Fragments: (project uses Activities; small projects may add fragments later)

MODULE II
- Views/ViewGroups: XML layouts (LinearLayout, ScrollView, FrameLayout)
- Orientation: layouts use match_parent/wrap_content and ScrollView for responsiveness
- ActionBar: app uses AppCompatActivity and default ActionBar
- Basic Views: TextView, Button, ImageView (where used), EditText, Spinner
- Picker Views: `DatePickerDialog` in `CareerDetailsActivity`

MODULE III
- Images: ImageView can be added to career details
- Menus: use activity menus where suitable (can be extended)
- WebView: `WebViewActivity` shows external resources
- SharedPreferences: `SettingsActivity` and `LoginActivity` store preferences
- Files: `SavedCareersActivity` exports `career_plan.txt` to internal storage
- SQLite: `CareerDatabaseHelper` provides saved careers storage

MODULE IV
- Content Provider: `CareerProvider` (minimal educational implementation)
- SMS/Email: Email intent used in `CareerDetailsActivity` (ACTION_SENDTO)
- Maps: Geo intent opened from `CareerDetailsActivity`
- Location: Map intent only; no continuous tracking to keep app simple

MODULE V
- Service: (Not implemented as background Service to keep example simple)
- Threading: used indirectly when exporting/IO; quiz analysis simulated with progress (can be extended)
- Notification: `CareerDetailsActivity` demonstrates creating a NotificationChannel and posting a reminder

Note: This project is an academic student project. Recommendations are rule-based, not machine-learning models.
