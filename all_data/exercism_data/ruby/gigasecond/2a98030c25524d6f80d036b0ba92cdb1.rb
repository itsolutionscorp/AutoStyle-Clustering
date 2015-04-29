class Gigasecond
  def initialize(start_date)
    @start = start_date.to_time
  end

  def date
    (start + (10 ** 9)).to_date
  end

  private

  attr_reader :start
end
