CREATE TABLE STATS (
  zipcode         INTEGER,
  seedingDate     DATE,
  precipIn        REAL,
  tempMin         REAL,
  tempMax         REAL
);

CREATE INDEX IX ON STATS(zipcode, seedingDate);