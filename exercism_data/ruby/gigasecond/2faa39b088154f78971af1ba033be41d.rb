class Gigasecond
  def initialize(day)
    @day = day
  end
  def date
    @day + (10**9)/86400
  end
end
