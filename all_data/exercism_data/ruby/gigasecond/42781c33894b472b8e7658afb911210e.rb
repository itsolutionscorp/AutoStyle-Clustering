class Gigasecond
  attr_reader :date

  def initialize(date)
    @date = (date.to_time + 1000000000).to_date
  end

end
