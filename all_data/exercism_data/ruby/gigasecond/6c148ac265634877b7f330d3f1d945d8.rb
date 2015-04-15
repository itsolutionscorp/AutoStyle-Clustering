require 'date'
require 'time'

class Gigasecond
  attr_reader :birthday
  def initialize(date)
    @birthday = date.to_time
  end

  def date
    (birthday + 10 ** 9).to_date

  end
end
