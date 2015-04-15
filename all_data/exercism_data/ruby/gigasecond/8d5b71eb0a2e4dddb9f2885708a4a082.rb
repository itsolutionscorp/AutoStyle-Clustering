class Gigasecond
  attr_reader :date

  def initialize(date)
    @date = date + gigaseconds_in_days
  end

  private

  def gigaseconds_in_days
    1_000_000_000 / seconds_per_day
  end

  def seconds_per_day
    60 * 60 * 24
  end
end
