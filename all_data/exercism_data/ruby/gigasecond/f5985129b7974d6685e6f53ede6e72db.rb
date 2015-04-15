class Gigasecond
  GIGASECOND = 1_000_000_000

  def initialize(date)
    @date = date
  end

  def date
    new_date = @date.to_time.to_i + GIGASECOND
    new_date = Time.at(new_date)
    Date.new(new_date.year, new_date.month, new_date.day)
  end
end
