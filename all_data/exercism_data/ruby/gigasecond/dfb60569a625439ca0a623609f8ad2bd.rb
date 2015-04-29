class Gigasecond
  attr_reader :date

  ONE_BILLION_SECONDS = 1_000_000_000

  def initialize(beginning_date)
    @date = (beginning_date.to_time + ONE_BILLION_SECONDS).to_date
  end

end
