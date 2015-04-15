class Gigasecond

  BILLION = 1E9

  # Add a billion seconds to the fromDate
  def self.from(fromDate)
	fromDate + BILLION
  end

end
