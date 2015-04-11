class Gigasecond

  def initialize(birth_date)
    # Lacking additional info assumes birth at midnight
    @birth_time = birth_date.to_time
  end

  def date
    # Returns date of birth time + Gigaseconds
    (@birth_time + 10**9).to_date
  end
end
