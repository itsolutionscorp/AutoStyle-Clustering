class Gigasecond
  
  attr_reader :date

  def initialize(date)
    @date = (date.to_time + 1_000_000_000.0).to_date
  end

end
