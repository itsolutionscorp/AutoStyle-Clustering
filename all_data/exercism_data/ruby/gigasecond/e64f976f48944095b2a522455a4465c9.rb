class Gigasecond
  def initialize(given_date)
    @given_date = given_date
  end

  def date
    given_date + one_billion_gigaseconds_to_days
  end

  private

  attr_reader :given_date

  def one_billion_gigaseconds_to_days
    10**9 / seconds_in_a_day
  end

  def seconds_in_a_hour
    60 * 60
  end

  def seconds_in_a_day
    24 * seconds_in_a_hour
  end
end
