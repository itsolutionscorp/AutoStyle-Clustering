class Gigasecond
  attr_accessor :now

  def initialize(now)
    @now = now
  end

  def date
    future = (10**9)/86400
    return self.now + future
  end

end
