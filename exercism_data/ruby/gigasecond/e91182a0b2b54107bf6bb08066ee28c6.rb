class Gigasecond
  def initialize start_date
    @start_date = start_date
  end

  def date
    @start_date + giga_seconds / seconds_per_day
  end

  private

  def seconds_per_day
    24*60*60
  end

  def giga_seconds
    1_000_000_000
  end
end
