class Gigasecond
  OFFSET = 1_000_000_000
  attr_reader :date

  def initialize(start_date)
    @date = (start_date.to_time + Gigasecond::OFFSET).to_date
  end
end
