class Gigasecond

  def initialize(date)
    @date = date
  end

  def date
    (@date.to_time + @@gigasecond).to_date
  end

  private

  @@gigasecond = 10**9

end
