class Gigasecond
  attr_accessor :date

  def initialize(date)
    @date = (date.to_time + 10**9).to_date
  end
end
