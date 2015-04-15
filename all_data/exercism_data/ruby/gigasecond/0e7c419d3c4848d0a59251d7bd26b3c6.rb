class Gigasecond
  def initialize(start)
    @start_second = start.to_time.to_i
  end

  def date
    Time.at(@start_second + 1e9).to_date
  end
end
