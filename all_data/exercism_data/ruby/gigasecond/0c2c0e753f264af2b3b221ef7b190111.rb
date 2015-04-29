class Gigasecond
  def initialize birthdate
    @birthdate = birthdate
  end

  def date
    @birthdate + DaysPerGigaSecond
  end

  private

  SecondsPerDay = 60*60*24
  DaysPerGigaSecond = 1_000_000_000 / SecondsPerDay
end
