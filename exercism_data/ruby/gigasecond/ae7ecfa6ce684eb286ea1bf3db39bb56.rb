class Gigasecond
  def initialize(date)
    @birth_date = date
  end

  def date
    time = @birth_date.to_time
    time += 10**9
    time.to_date
  end
end
