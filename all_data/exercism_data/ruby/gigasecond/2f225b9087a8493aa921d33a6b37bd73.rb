require 'date'
require 'time'

class Gigasecond
  attr_reader :date

  A_GIGASECOND = 10**9

  def initialize(birth_date)
    @date = (birth_date.to_time + A_GIGASECOND).to_date
  end
end
