class Gigasecond
  def initialize(date)
    @date = date.to_time
  end

  def date
    new_date = @date + gigasecond
    Time.at(new_date).to_date
  end

  private

  def gigasecond
    10**9
  end
end
