class Gigasecond

  def self.from(time)
    Gigasecond.new(time).add_one_gigasecond 
  end

  def initialize(time)
    @start = time
  end

  def add_one_gigasecond
    @start + 10**9
  end
end
