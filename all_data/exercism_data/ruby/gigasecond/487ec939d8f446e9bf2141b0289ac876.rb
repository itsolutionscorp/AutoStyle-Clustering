class Gigasecond
  GIGA = 10 ** 9

  class << self
    def from(origin_date)
      new(origin_date).advance.to_date
    end
  end

  def initialize(origin_date)
    @origin_date = origin_date
    @current_time = @origin_date.to_time
  end

  def advance(seconds = GIGA)
    @current_time += seconds
  end

  def to_date
    @current_time.to_date
  end
end
