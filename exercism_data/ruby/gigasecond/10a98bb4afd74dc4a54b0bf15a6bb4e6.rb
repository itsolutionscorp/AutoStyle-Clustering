require 'date'

class Gigasecond
  # define constant to reuse gigasecond
  GIGASECOND = 1000000000

  # time constants
  SEC_PER_MIN  = 60
  MIN_PER_HOUR = 60
  HRS_PER_DAY  = 60

  def self.from(date)
    date + (GIGASECOND / (SEC_PER_MIN * MIN_PER_HOUR * HRS_PER_DAY))
  end
end
