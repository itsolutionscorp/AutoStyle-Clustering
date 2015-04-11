class Gigasecond
  attr_reader :time

  GIGASECOND = 10**9

  def initialize(time)
    @time = time
  end

  def self.from(time)
    new(time).from
  end

  def from
    time + GIGASECOND
  end
end
