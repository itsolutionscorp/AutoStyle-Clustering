class Gigasecond
  def initialize(date)
    @date = date.to_time
  end

  def date
    (@date + 1_000_000_000).to_date
  end
end
