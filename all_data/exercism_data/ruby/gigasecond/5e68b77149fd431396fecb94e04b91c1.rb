class Gigasecond
  attr_reader :time

  def initialize date
    @time = date.to_time
  end

  def date
    (time + 10**9).to_date
  end
end
