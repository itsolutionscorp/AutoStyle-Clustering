class Gigasecond
  attr_reader :date

  def initialize(beginning_date)
    @date = (beginning_date.to_time + 1000000000).to_date
  end

end
