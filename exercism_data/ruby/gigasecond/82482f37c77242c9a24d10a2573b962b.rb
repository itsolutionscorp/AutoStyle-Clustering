class Gigasecond
  GIGASECOND = 10**9
  GS_IN_DAYS = GIGASECOND / 60 / 60 / 24

  def self.from (date)
    date + GS_IN_DAYS
  end
end
