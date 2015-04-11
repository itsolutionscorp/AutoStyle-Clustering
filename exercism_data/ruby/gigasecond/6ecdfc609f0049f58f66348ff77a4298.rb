class Gigasecond
  SECONDS = 10**9

  def initialize(birthday)
    @time = birthday.to_time
  end

  def date
    (@time + SECONDS).to_date
  end
end
