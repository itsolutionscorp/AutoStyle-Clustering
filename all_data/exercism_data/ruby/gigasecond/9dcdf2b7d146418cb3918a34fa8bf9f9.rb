class Gigasecond

  def initialize(std_date)
    @std_date = std_date
  end

  def date
    (@std_date.to_time + 1_000_000_000).to_date
  end
end
