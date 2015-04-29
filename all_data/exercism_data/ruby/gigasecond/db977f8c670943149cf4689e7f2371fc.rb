class Gigasecond
  BILLION = 1_000_000_000

  attr_reader :date

  def initialize(date) 
    @date = (date.to_time + 1 * BILLION).to_date
  end
end
