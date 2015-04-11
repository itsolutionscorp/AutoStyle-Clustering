class Gigasecond
  ADDED_DAYS = 1_000_000_000/86_400
  def initialize(date)
    @date = date
  end
  def date
    @date + ADDED_DAYS
  end
end
