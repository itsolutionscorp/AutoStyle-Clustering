class Gigasecond
  SECONDS_IN_A_HOUR = 60 * 60
  SECONDS_IN_A_DAY = 24 * SECONDS_IN_A_HOUR
  ONE_BILLION_GIGASECONDS_TO_DAYS = 10**9 / SECONDS_IN_A_DAY

  def initialize(given_date)
    @given_date = given_date
  end

  def date
    given_date + ONE_BILLION_GIGASECONDS_TO_DAYS
  end

  private

  attr_reader :given_date
end
