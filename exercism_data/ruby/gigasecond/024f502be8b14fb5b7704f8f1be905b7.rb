class Gigasecond
  ONE_GIGA_SECONDS = 10**9
  SECONDS_IN_A_DAY = 60 * 60 * 24
  STILL_FIGURING_OUT = 6400 # 60 * 100 + 400 # 1:46:40
  ONE_GIGA_SECONDS_IN_DAYS = Rational(ONE_GIGA_SECONDS, SECONDS_IN_A_DAY) -
                             Rational(STILL_FIGURING_OUT, SECONDS_IN_A_DAY)

  def Gigasecond.from(date)
    date + ONE_GIGA_SECONDS_IN_DAYS
  end

end
