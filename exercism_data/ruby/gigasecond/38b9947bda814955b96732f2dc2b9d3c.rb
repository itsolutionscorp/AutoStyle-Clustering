class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    (@date.to_time + SECONDS).to_date
  end

  private

  SECONDS = 10**9

end
