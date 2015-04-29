module Gigasecond
  GIGASECOND = 10**9
  def self.from(date)
    date + Gigasecond.to_days
  end

  def self.to_days
    GIGASECOND / 60 / 60 / 24
  end
end
