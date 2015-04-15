class Gigasecond

GIGASECOND = 1_000_000_000

  def initialize(date)
    @date = date 
  end

  def date
    (@date.to_time + GIGASECOND).to_date
  end
end
