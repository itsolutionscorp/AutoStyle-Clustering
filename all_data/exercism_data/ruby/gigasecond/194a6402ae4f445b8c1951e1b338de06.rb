class Gigasecond
  attr_reader :giga_date
  def initialize(date)
    @giga_date = date.to_time
  end

  def date
    (giga_date + 1_000_000_000).to_date
  end
end
