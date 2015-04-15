class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    seconds = @date.to_time.to_i + 10**9
    Time.at(seconds).to_date
  end

end
