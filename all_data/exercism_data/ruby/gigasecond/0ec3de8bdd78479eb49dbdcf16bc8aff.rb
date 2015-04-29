require 'date'
require 'time'

class Gigasecond
  attr_accessor :date

  def initialize(date)
    @date = (date.to_time + 10**9).to_date
  end

  def self.from(date)
    Gigasecond.new(date)
  end
end
