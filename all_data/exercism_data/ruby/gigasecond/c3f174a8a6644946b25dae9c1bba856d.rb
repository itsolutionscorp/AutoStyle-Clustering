class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    Time.at(@date.to_time.to_i + 1e9).to_date
  end
end
