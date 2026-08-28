# Job Radar — Development, Git, CI/CD & Deployment Roadmap

## 1. Project Goal

Build a production-style **AI-powered Job Radar** that:

- Fetches newly posted jobs from selected companies/sectors
- Filters jobs based on my experience, location, skills and target roles
- Removes irrelevant jobs such as Senior/Lead roles
- Ranks jobs by relevance
- Analyzes job descriptions
- Suggests resume keywords
- Matches jobs against my resume
- Identifies potentially useful LinkedIn connections for referrals
- Notifies me when relevant new jobs appear
- Provides a dashboard to view and manage jobs

The project should be built like a **real production application**, not just as a local college project.

---

# 2. Main Technology Stack

## Frontend

- Angular
- TypeScript
- HTML/CSS
- Angular HTTP Client

## Backend

- Java 21 LTS
- Spring Boot
- Spring Web / REST APIs
- Spring Data JPA
- Maven

## Database

- MySQL

## AI / ML

- Python
- LangGraph
- LangChain
- LLM API
- Embeddings / vector search where useful

The AI component will initially be a separate Python service rather than mixing Python directly into the Spring Boot backend.

## Job Data Collection

Possible approaches:

- Company career APIs
- Public job feeds
- ATS APIs/endpoints
- RSS feeds where available
- Carefully implemented public-page fetching where permitted

Do NOT build the system around blindly scraping every website.

## Notifications

Initially:

- Email

Later:

- Browser notifications if useful

## Testing

Backend:

- JUnit
- Mockito
- Spring Boot Test

Frontend:

- Angular testing tools

AI service:

- Python testing framework

## API Testing

- Postman or Thunder Client

## Version Control

- Git
- GitHub

## CI/CD

- GitHub Actions

## Containerization

- Docker

## Deployment

Use free-tier/free-usage options where available.

Possible services:

- Render
- Railway
- Vercel
- Supabase / Neon where appropriate
- GitHub Pages for static frontend if appropriate

Always verify current free-tier limits before deployment.

---

# 3. Overall Architecture

```text
                         USER
                           |
                           v
                    Angular Frontend
                           |
                           | REST API
                           v
                   Spring Boot Backend
                           |
             +-------------+-------------+
             |             |             |
             v             v             v
           MySQL       Job Fetcher    AI Service
                           |             |
                           |          LangGraph
                           |             |
                           |            LLM
                           |
                           v
                    Job Sources / ATS
                           
                           |
                           v
                     Notification
                        Service
                           |
                           v
                         Email
4. Repository Structure

The repository can eventually look like:

job-radar/
│
├── backend/
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
│
├── frontend/
│   ├── src/
│   └── package.json
│
├── ai-service/
│   ├── app/
│   ├── requirements.txt
│   └── ...
│
├── docker/
│   └── ...
│
├── .github/
│   └── workflows/
│       ├── backend-ci.yml
│       ├── frontend-ci.yml
│       └── deploy.yml
│
├── docker-compose.yml
│
├── README.md
└── .gitignore

The exact structure can change as the project evolves.

5. Git Strategy

The project should use Git from the beginning.

Main branches
main
develop
main

Production-ready code.

Nothing should be pushed directly to main.

develop

Integration branch where completed features are combined before production.

6. Feature Branches

Every feature gets its own branch.

Example:

feature/job-api
feature/job-fetcher
feature/job-filtering
feature/resume-analysis
feature/ai-matching
feature/notification-system
feature/referral-matching
feature/dashboard

Example workflow:

develop
   |
   +---- feature/job-api
   |
   |       development
   |       commits
   |
   +---- Pull Request
              |
              v
           CI Tests
              |
              v
        Code Review
              |
              v
          merge
              |
              v
           develop

I should practice:

git checkout develop
git pull
git checkout -b feature/job-api

Work:

git add .
git commit -m "Add job API"
git push -u origin feature/job-api

Then create a Pull Request:

feature/job-api
       ↓
    develop
7. Pull Requests

Every feature should go through a Pull Request.

The PR should trigger CI automatically.

Example:

Developer
    |
    v
Push feature branch
    |
    v
Create PR
    |
    v
GitHub Actions
    |
    +---- Compile
    +---- Unit Tests
    +---- Integration Tests
    +---- Code Quality
    +---- Build
    |
    v
   PASS
    |
    v
Merge into develop

This teaches the same workflow used in professional teams.

8. CI — Continuous Integration

CI means automatically validating code whenever changes are pushed.

For the backend:

Checkout repository
        ↓
Setup Java 21
        ↓
Setup Maven
        ↓
Download dependencies
        ↓
Compile
        ↓
Run unit tests
        ↓
Run integration tests
        ↓
Package application

For frontend:

Checkout
   ↓
Setup Node
   ↓
npm install
   ↓
Lint
   ↓
Test
   ↓
Build

For AI service:

Checkout
   ↓
Setup Python
   ↓
Install dependencies
   ↓
Run tests
   ↓
Lint

CI should fail if any important step fails.

9. Docker

Once the application works locally, containerize it.

Possible containers:

Angular
Spring Boot
Python AI Service
MySQL

Local development:

Docker Compose
      |
      +---- Backend
      +---- AI Service
      +---- MySQL

Benefits:

Consistent environments
Easier setup
Easier deployment
Understanding containers
Production-like development
10. Environments

The project should have multiple environments.

Development
     ↓
Staging
     ↓
Production
Development

Used locally.

localhost

Purpose:

Build features
Debug
Experiment
Staging

A deployed environment that behaves as closely as possible to production.

Example:

staging-job-radar.example

Purpose:

Test integrated features
Test APIs
Test database interactions
Test AI functionality
Test deployment
Catch bugs before production
Production

The real application.

job-radar.example

Only tested code should reach production.

11. Deployment Flow

The desired flow is:

feature branch
      ↓
Pull Request
      ↓
CI
      ↓
merge to develop
      ↓
CI
      ↓
deploy to STAGING
      ↓
manual/integration testing
      ↓
Pull Request
      ↓
develop → main
      ↓
CI
      ↓
deploy to PRODUCTION

This gives the project a realistic development lifecycle.

12. Database Strategy

Local:

MySQL
localhost

Staging:

Separate staging database

Production:

Separate production database

Never use the production database for local development.

Never commit:

DB passwords
API keys
LLM keys
Email passwords
JWT secrets

into GitHub.

Use:

Environment variables
GitHub Secrets
Deployment platform secrets
13. Configuration

Different environments need different configuration.

Example:

Development
    DB_URL=localhost
    AI_SERVICE_URL=localhost
Staging
    DB_URL=staging-db
    AI_SERVICE_URL=staging-ai-service
Production
    DB_URL=production-db
    AI_SERVICE_URL=production-ai-service

The application code should not contain these values directly.

14. Job Data Pipeline

The job system will eventually work like:

Company / ATS
     ↓
Job Fetcher
     ↓
Raw Job
     ↓
Normalize Job
     ↓
Store in MySQL
     ↓
Deduplicate
     ↓
Filter
     ↓
Experience Check
     ↓
Role Check
     ↓
Location Check
     ↓
Skill Matching
     ↓
AI Relevance Score
     ↓
Relevant Jobs
     ↓
Notification
15. Filtering

The user should be able to specify:

Target companies:
- JPMorgan
- American Express
- HSBC
- Microsoft
- Google
- etc.

Sector:
- Banking
- FinTech
- Software
- AI/ML

Experience:
- 0–2 years

Location:
- India
- Chennai
- Bangalore
- Hyderabad
- Remote

Role:
- Software Engineer
- Backend Engineer
- Java Developer
- AI Engineer

The system should eliminate jobs such as:

Senior Software Engineer
Staff Engineer
Principal Engineer
Engineering Manager
10+ years experience

before presenting them.

16. AI Job Matching

AI should not replace normal filtering.

Use a hybrid approach:

Hard Filters
     ↓
Experience
Location
Role
Company
     ↓
Candidate Jobs
     ↓
AI Relevance Analysis
     ↓
Relevance Score

Example:

Job:
Software Engineer – Java

Required:
Java
Spring Boot
SQL
REST APIs
1–3 years

Candidate:
Java
Spring Boot
SQL
REST
1 YOE

AI Match:
91%

The AI should also explain:

Why this job matches
Missing skills
Important keywords
Resume keywords to emphasize
17. Resume Analysis

The user uploads a resume.

Pipeline:

Resume
   ↓
Extract text
   ↓
Identify skills
   ↓
Identify experience
   ↓
Identify projects
   ↓
Compare with Job Description
   ↓
Missing keywords
   ↓
Suggested resume improvements

The system should suggest changes rather than automatically invent experience.

18. Referral Matching

The system can maintain a list of the user's professional contacts.

For a relevant company:

Job
 ↓
Company
 ↓
Search user's contacts
 ↓
Matching company/contact
 ↓
Suggest:
"Consider asking X for a referral."

The system should not automatically contact people.

It should only provide a recommendation.

19. Notifications

A background job periodically checks for new jobs.

Example:

Scheduler
    ↓
Fetch jobs
    ↓
Compare with existing jobs
    ↓
New job?
    ↓
Relevant?
    ↓
YES
    ↓
Send notification

Example notification:

New Job Match

Company: JPMorgan
Role: Software Engineer
Experience: 1–3 years
Match: 92%

Why:
✓ Java
✓ Spring Boot
✓ SQL
✓ 1 YOE

Apply now.
20. Scheduling

The job fetcher should eventually run automatically.

For example:

Every 30 minutes
        ↓
Fetch jobs
        ↓
Process
        ↓
Store
        ↓
Notify

This introduces concepts such as:

Scheduled jobs
Background processing
Idempotency
Deduplication
Retry logic
21. Error Handling

Real systems fail.

Examples:

Job source unavailable
API timeout
Database unavailable
LLM API failure
Email failure
Invalid job data

The application should handle these gracefully.

Example:

Job Fetcher
    ↓
API timeout
    ↓
Retry
    ↓
Still failing?
    ↓
Log error
    ↓
Continue other sources

One failed company source should not crash the entire application.

22. Logging & Monitoring

Learn:

Application logs
Error logs
HTTP status codes
Health checks
Basic metrics

Spring Boot should expose a health endpoint.

Example:

/actuator/health

Eventually:

Application
    ↓
Health Check
    ↓
Monitoring
23. Testing Strategy

Tests should exist at multiple levels.

Unit Tests

Test individual classes/methods.

JobFilterTest
JobMatcherTest
ResumeAnalyzerTest
Integration Tests

Test multiple components together.

Controller
    ↓
Service
    ↓
Repository
    ↓
Database
API Testing

Use Postman/Thunder Client.

End-to-End Testing

Eventually:

Frontend
   ↓
Backend
   ↓
Database
   ↓
AI
24. CI/CD Pipeline

The eventual GitHub Actions setup should look approximately like:

                    GitHub
                       |
                       v
                Pull Request
                       |
                       v
                    CI
          +------------+------------+
          |            |            |
       Backend      Frontend       AI
        Tests         Tests        Tests
          |            |            |
          +------------+------------+
                       |
                       v
                    BUILD
                       |
                       v
                  Merge develop
                       |
                       v
                  Deploy STAGING
                       |
                       v
                Integration Tests
                       |
                       v
                 Manual Approval
                       |
                       v
                  Merge main
                       |
                       v
                    CI
                       |
                       v
              Deploy PRODUCTION
25. GitHub Actions

GitHub Actions will be our main CI/CD tool.

It can:

Build Java
Run Maven tests
Build Angular
Run frontend tests
Test Python
Build Docker images
Deploy applications
Run scheduled workflows
Manage environment-specific secrets

The basic concept:

GitHub Event
     ↓
Workflow
     ↓
Runner
     ↓
Commands
     ↓
Result
26. Secrets

Never commit:

OPENAI_API_KEY
GROQ_API_KEY
DATABASE_PASSWORD
JWT_SECRET
EMAIL_PASSWORD

Instead:

GitHub Secrets
      ↓
CI/CD
      ↓
Environment Variables
      ↓
Application

Different environments should have different secrets where necessary.

27. Production-Like Branch + Environment Model

Final model:

                    main
                     |
                PRODUCTION
                     ↑
                  PR / CI
                     |
                  develop
                     |
                 STAGING
                     ↑
                  PR / CI
                     |
              feature branches

Example:

feature/job-fetcher
        ↓
       PR
        ↓
     develop
        ↓
    STAGING
        ↓
     testing
        ↓
       PR
        ↓
      main
        ↓
   PRODUCTION
28. Free Tools

The goal is to build the project without paying for infrastructure.

Development
VS Code
Java 21 LTS
Spring Boot
Maven
Node.js
Angular CLI
Python
Git
Docker Desktop
Source Control
GitHub Free
CI/CD
GitHub Actions free usage within applicable limits
API Testing
Postman Free
Thunder Client
Database
MySQL Community Edition
AI

Use a free-tier or free-access LLM/API where available.

Possible choices can change over time, so check current limits before committing the architecture to one provider.

Deployment

Use free-tier platforms where currently available.

Possible options:

Render
Vercel
GitHub Pages
Supabase
Neon

Free tiers and limitations can change, so deployment choices should be finalized when we reach that stage.

29. What I Will Learn

This project should teach the following.

Java
OOP
Collections
Exceptions
Streams
Interfaces
Generics
Multithreading basics
Spring Boot
REST APIs
Controllers
Services
Repositories
Dependency Injection
Configuration
Profiles
Validation
Exception handling
Spring Data JPA
Transactions
Spring Security basics
Actuator
Database
MySQL
SQL
Joins
Indexes
Transactions
Normalization
JPA/Hibernate
Frontend
Angular
Components
Services
Routing
HTTP
Forms
State management basics
AI
LLMs
Prompt engineering
Structured output
Function/tool calling
Embeddings
RAG
LangChain
LangGraph
AI agents
Evaluation
AI service architecture
Backend Architecture
REST
Layered architecture
DTOs
Validation
Error handling
Authentication
Authorization
Caching
Background jobs
Git
Branches
Commits
Pull Requests
Merge
Rebase
Conflict resolution
Git workflows
CI/CD
GitHub Actions
CI pipelines
Automated testing
Build pipelines
Artifacts
Deployment pipelines
Environment variables
Secrets
Staging
Production
DevOps
Docker
Containers
Docker Compose
Environment configuration
Logs
Health checks
Deployment
System Design
Service boundaries
API design
Database design
Scalability
Reliability
Retry mechanisms
Scheduling
Caching
Asynchronous processing
30. Development Phases
Phase 1 — Environment

Set up:

Java 21
Maven
Spring Boot
Node.js
Angular
Python
Git
GitHub
Docker
Phase 2 — Backend Foundation

Create:

Spring Boot
REST API
Controller
Service
Repository
MySQL

Learn:

Controller → Service → Repository → Database
Phase 3 — Git Workflow

Create:

main
develop
feature/*

Practice:

Feature
 ↓
Commit
 ↓
Push
 ↓
PR
 ↓
Merge
Phase 4 — CI

Create GitHub Actions.

Every PR should:

Build
 ↓
Test
 ↓
Validate
Phase 5 — Frontend

Build the Angular dashboard.

Connect:

Angular
   ↓
REST API
   ↓
Spring Boot
Phase 6 — Job Collection

Implement job sources.

Build:

Fetch
 ↓
Normalize
 ↓
Deduplicate
 ↓
Store
Phase 7 — Filtering

Implement:

Company
Experience
Location
Role
Skills
Phase 8 — AI

Add:

Job Description
       ↓
LLM
       ↓
Match Analysis
       ↓
Score
       ↓
Resume Keywords
Phase 9 — Notifications

Add:

Scheduler
 ↓
New Job Detection
 ↓
Relevance
 ↓
Email
Phase 10 — Docker

Containerize the services.

Docker Compose
    ├── Backend
    ├── AI
    └── MySQL
Phase 11 — Staging

Deploy a staging version.

develop
   ↓
CI
   ↓
STAGING

Test the complete application.

Phase 12 — Production

Once staging is stable:

develop
   ↓
PR
   ↓
CI
   ↓
main
   ↓
Production deployment
31. Final Production-Style Workflow

The final workflow I want to follow throughout the project is:

1. Pick a feature
       ↓
2. Create feature branch
       ↓
3. Develop locally
       ↓
4. Run tests
       ↓
5. Commit
       ↓
6. Push branch
       ↓
7. Open Pull Request
       ↓
8. GitHub Actions CI
       ↓
9. Review
       ↓
10. Merge into develop
       ↓
11. CI runs
       ↓
12. Deploy to staging
       ↓
13. Test staging
       ↓
14. Fix issues if necessary
       ↓
15. PR develop → main
       ↓
16. CI runs again
       ↓
17. Deploy production
       ↓
18. Monitor

The objective is not merely to make the Job Radar work.

The objective is to learn how a real software team develops, tests, integrates, deploys and maintains an application, while simultaneously building a genuinely useful AI-powered product.

