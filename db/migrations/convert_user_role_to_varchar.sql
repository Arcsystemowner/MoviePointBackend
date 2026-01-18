-- Migration: convert_user_role_to_varchar.sql
-- Purpose: Change the Postgres enum column `role` (type user_role)
-- to a standard varchar so the application can store enum names.

BEGIN;

-- 1) Convert existing enum values to text and change the column type
ALTER TABLE "users"
  ALTER COLUMN role TYPE varchar USING role::text;

COMMIT;

-- Note: Review application behavior and backups before applying.