class Gigasecond
  def initialize d
    @time = d.to_time + 1_000_000_000
  end

  def date
    @time.to_date
  end
end
