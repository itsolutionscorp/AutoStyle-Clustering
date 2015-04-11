class Gigasecond

  @@a_billion_seconds_in_days = 1_000_000_000 / (60 * 60 * 24)

  def initialize(from_date)
    @from_date = from_date
  end

  def date
    from_date + @@a_billion_seconds_in_days
  end

  private

  attr_reader :from_date

end
