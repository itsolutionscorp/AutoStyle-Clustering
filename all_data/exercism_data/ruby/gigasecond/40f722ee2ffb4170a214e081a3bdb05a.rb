class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    gigaseconds = 1000000000
    time = @date.to_time + gigaseconds
    date = time.to_date
  end
end
