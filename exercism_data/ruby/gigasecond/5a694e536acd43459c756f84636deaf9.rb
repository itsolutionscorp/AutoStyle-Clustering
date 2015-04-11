class Gigasecond
  attr_reader :date

  def initialize(start_date)
    @date = (start_date.to_time + 1_000_000_000).to_date
  end
end
