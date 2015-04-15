class Gigasecond
  GIGASECOND = 10**9
  @gigasecond_days = GIGASECOND / 60 / 60 / 24

  def self.from( day )
    day + @gigasecond_days
  end
end
