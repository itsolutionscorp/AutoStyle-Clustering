class Gigasecond
  def initialize(date)
    @date = date
    @gigasec_in_days = 1_000_000_000 / 60 / 60 / 24
  end

  def date
    @date + @gigasec_in_days
  end
end
