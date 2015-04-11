class Gigasecond

    GIGASECOND = 10**9

    SECONDS_IN_A_DAY = 60 * 60 * 24

    DAYS_TO_SUM = (GIGASECOND)/(SECONDS_IN_A_DAY)
  def self.from(birthDay)
    birthDay + DAYS_TO_SUM
  end
end
