require 'date'
require 'time'

class Gigasecond
  attr_accessor :date

  def initialize(date)
    seconds = date_to_seconds(date) + giga
    @date = seconds_to_date(seconds)
  end

  private

  def date_to_seconds(date)
    date.to_time.to_i
  end

  def seconds_to_date(seconds)
    Time.at(seconds).to_date
  end

  def giga
    10**9
  end

end
