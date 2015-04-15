class Gigasecond

  def initialize(date)
    @time = date.to_time
  end

  def date
    (@time + 10**9).to_date
  end

end
