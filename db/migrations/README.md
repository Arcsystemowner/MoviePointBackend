Migration: convert_user_role_to_varchar

Steps to apply locally or in production:

1. Backup the database.

2. Test migration on a staging copy:

   ```bash
   psql "$DB_URL" -c "\i db/migrations/convert_user_role_to_varchar.sql"
   ```

3. If using a migration tool (Flyway/Liquibase), add the SQL to your migration folder so it runs automatically.

4. After migration, restart the application and verify `POST /api/auth/register` works.

Notes:

- If you prefer to keep the Postgres enum type, implement a JPA AttributeConverter or Hibernate custom type instead.
- Always backup and test before applying to production.
