class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    (@date.to_time + 1_000_000_000).to_date
  end
end
