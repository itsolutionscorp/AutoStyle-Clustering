require 'date'
require 'time'

class Gigasecond

  attr_accessor :date

  ## Constants
  SECONDS = 10**9
  SECONDS_IN_DAY = 60 * 60 * 24

  def initialize(input_date)
    # input_date is a Date so we need to convert seconds into days
    self.date = input_date + (SECONDS / SECONDS_IN_DAY)
  end

end
