module Gigasecond
  # Define a constant containing the number of days
  # in a gigasecond. Convert to an integer because
  # tests fail otherwise.
  #
  DAYS_IN_GIGASECOND = 10**9 / (24 * 60 * 60.0).to_i

  def self.from(date)
    date + DAYS_IN_GIGASECOND
  end
end
