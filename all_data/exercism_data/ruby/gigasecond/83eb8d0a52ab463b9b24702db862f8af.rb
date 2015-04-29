class Gigasecond
  SECONDS = 1_000_000_000

  def initialize(date)
    @date = date
  end

  def date
    (@date.to_time + SECONDS).to_date
  end
end
