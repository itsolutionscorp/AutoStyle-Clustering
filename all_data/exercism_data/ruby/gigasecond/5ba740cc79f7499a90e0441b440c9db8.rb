class Gigasecond
	SECONDS = 10**9

  def initialize(date)
    @date = date
  end

  def date
    (@date.to_time + SECONDS).to_date
  end

end
