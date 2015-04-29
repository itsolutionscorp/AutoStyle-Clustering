class Gigasecond

  OFFSET = 10**9 / (60 * 60 * 24)

  attr_reader :start

  def initialize(start)
    @start = start
  end

  def date
    @start + OFFSET
  end

end
