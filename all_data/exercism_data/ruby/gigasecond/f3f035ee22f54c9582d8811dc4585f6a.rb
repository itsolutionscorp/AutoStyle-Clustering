class Gigasecond
  attr_reader :date

  def initialize(start_date)
    @date = (start_date.to_time + 1e9).to_date
  end
end
