CREATE TABLE STATS (
  zipcode         INTEGER,
  seedingDate     DATE,
  precipIn        REAL,
  tempMin         REAL,
  tempMax         REAL,
  lat             REAL,
  lng             REAL
);

CREATE INDEX IX ON STATS(zipcode, seedingDate);