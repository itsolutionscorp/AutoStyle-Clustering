class Gigasecond
  def initialize(date)
    @date = date.to_time
  end

  def date
    (@date + 1000000000).to_date
  end
end
