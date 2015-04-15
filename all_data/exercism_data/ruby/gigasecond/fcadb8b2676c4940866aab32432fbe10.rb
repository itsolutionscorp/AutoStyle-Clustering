class Gigasecond

  GIGASECONDS_IN_DAYS = (10**9)/60/60/24

  def self.from(start)
    start += GIGASECONDS_IN_DAYS
  end

end
