require 'date'
require 'time'

class Gigasecond
  GS = 10**9

  def self.from(date)
    new(date).calculate
  end

  def initialize(date)
    @date = date
    @time = Time.new(@date.year, @date.month, @date.day)
  end

  def calculate
    Time.at(@time.to_i + GS).to_date
  end
end
