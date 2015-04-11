class Gigasecond

  attr_reader :date

  def initialize(start_date)
    @date = start_date + (1_000_000_000/(3600*24))
  end

  def add_time(seconds)
    @date
  end

end
