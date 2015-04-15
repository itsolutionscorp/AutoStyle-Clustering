class Gigasecond
  DAY_IN_GIGASECONDS = 11574

  def self.from(date)
    date + DAY_IN_GIGASECONDS
  end

end
