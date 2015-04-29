class Gigasecond
  ONE_GIGA_SECONDS         = 10**9
  SECONDS_IN_A_DAY         = 60 * 60 * 24
  ONE_GIGA_SECONDS_IN_DAYS = ONE_GIGA_SECONDS / SECONDS_IN_A_DAY

  def Gigasecond.from(date)
    date + ONE_GIGA_SECONDS_IN_DAYS
  end

end
