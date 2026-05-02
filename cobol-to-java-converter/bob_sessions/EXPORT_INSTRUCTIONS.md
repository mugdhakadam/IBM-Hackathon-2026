# Quick Export Instructions for Bob IDE Task Sessions

## Step-by-Step Guide

### 1. Open Bob IDE History
- In Bob IDE, click: **Views** → **More Actions** → **History**
- Select **All** or your specific workspace

### 2. For Each Task You Want to Export

#### A. View Task Session Summary
1. Click on the task in the history list
2. Click the **task header** at the top
3. A **Task Session Consumption Summary** popup will appear

#### B. Capture Screenshot
1. Take a screenshot of the consumption summary
2. Save it with a descriptive name (e.g., `01_parser_implementation_summary.png`)

#### C. Export Task History
1. Click the **Export task history** icon (down-arrow icon)
2. Save the Markdown file with a matching name (e.g., `01_parser_implementation_summary.md`)

### 3. Add Files to This Folder

Move both files (`.md` and `.png`) to the `bob_sessions` folder:

```
bob_sessions/
├── 01_task_name_summary.md
├── 01_task_name_summary.png
├── 02_task_name_summary.md
├── 02_task_name_summary.png
└── ...
```

### 4. Commit to Git

```bash
# From the project root directory
git add bob_sessions/
git commit -m "Add Bob IDE task session reports for IBM judging"
git push origin main
```

## Recommended Tasks to Export

Export tasks that demonstrate:
- ✅ Initial project setup and architecture
- ✅ Core feature implementation (parser, generator, analyzer)
- ✅ Bug fixes and debugging sessions
- ✅ Documentation creation
- ✅ Testing and validation
- ✅ Code refactoring and optimization
- ✅ Integration and deployment

## File Naming Convention

Use clear, sequential naming:
- `01_initial_setup_summary.md` / `01_initial_setup_summary.png`
- `02_cobol_parser_summary.md` / `02_cobol_parser_summary.png`
- `03_java_generator_summary.md` / `03_java_generator_summary.png`
- etc.

## Tips

- **Be comprehensive**: Include all significant development tasks
- **Be organized**: Use consistent numbering and naming
- **Be clear**: Screenshots should be readable and show key metrics
- **Be thorough**: Don't skip tasks that show Bob's problem-solving capabilities

## Verification Checklist

Before submitting:
- [ ] All major development tasks are exported
- [ ] Each task has both .md and .png files
- [ ] Files are named consistently and descriptively
- [ ] Screenshots are clear and readable
- [ ] Files are committed to the repository
- [ ] Changes are pushed to remote repository

---

**For IBM Hackathon Judging**  
These reports demonstrate how Bob AI IDE was utilized throughout the project development lifecycle.