class Gigasecond

  def initialize(date_of_birth)
    @date_of_birth = date_of_birth
  end

  def date
    date_of_birth + days_till_gigasecond
  end

  private

  attr_reader :date_of_birth

  def days_till_gigasecond
    GIGASECOND / SECONDS_PER_DAY
  end

  SECONDS_PER_DAY = 60 * 60 * 24

  GIGASECOND = 1000_000_000

end
