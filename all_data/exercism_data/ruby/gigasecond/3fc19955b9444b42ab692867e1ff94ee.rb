class Gigasecond
  attr_reader :start

  def initialize(start)
    @start = start
  end

  def date
    start + days_in_gigasecond
  end

  def days_in_gigasecond
    1_000_000_000 / (60 * 60 * 24)
  end
end
