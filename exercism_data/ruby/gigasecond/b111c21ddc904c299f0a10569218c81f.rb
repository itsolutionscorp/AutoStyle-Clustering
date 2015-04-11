class Gigasecond

  IN_DAYS = 1_000_000_000 / (60 * 60 * 24)

  def initialize(from_date)
    @from_date = from_date
  end

  def date
    from_date + Gigasecond::IN_DAYS
  end

  private

  attr_reader :from_date

end
