class Gigasecond

  GIGASECONDS = 10**9

  def self.from(start)
    start = start.to_time if start.respond_to?(:to_time)
    start += GIGASECONDS
    start.to_date
  end

end
