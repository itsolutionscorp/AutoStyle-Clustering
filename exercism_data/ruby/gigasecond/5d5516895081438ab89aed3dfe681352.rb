class Gigasecond
  SECONDS_IN_A_DAY = 60*60*24
  DAYS_TO_GIGABIRTHDAY = 10**9 / SECONDS_IN_A_DAY

  def self.from(birth_date)
    birth_date + DAYS_TO_GIGABIRTHDAY
  end
end
