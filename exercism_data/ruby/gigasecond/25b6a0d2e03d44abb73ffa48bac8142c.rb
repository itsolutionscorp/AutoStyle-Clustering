class Gigasecond
  GIGASECOND = 1_000_000_000

  def initialize date
    @date = date
  end

  def date
    @date + days_in_a_gigasecond
  end

  private

  def days_in_a_gigasecond
    GIGASECOND / (24 * 60 * 60)
  end
end
