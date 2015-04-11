class Gigasecond
  attr_reader :date

  def initialize(start_date)
    @start_date = start_date
  end

  def gigasecond_after
    new_time = @start_date.to_time + ONE_BILLION_SECONDS
    @date = new_time.to_date
  end

  ONE_BILLION_SECONDS = 1_000_000_000

end
