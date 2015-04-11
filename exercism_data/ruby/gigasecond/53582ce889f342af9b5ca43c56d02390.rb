class Gigasecond

  ONE_BILLION_SECONDS = 1_000_000_000

  attr_reader :date

  def initialize(start_date)
    @date = gigasecond_after(start_date)
  end

  def gigasecond_after(start_date)
    (start_date.to_time + ONE_BILLION_SECONDS).to_date
  end

end
