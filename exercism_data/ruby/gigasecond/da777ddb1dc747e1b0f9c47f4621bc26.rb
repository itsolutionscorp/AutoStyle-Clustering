class Gigasecond
  def initialize(date)
    @date = (date.to_time + 1000000000).to_date
  end

  def date
    @date
  end
end
