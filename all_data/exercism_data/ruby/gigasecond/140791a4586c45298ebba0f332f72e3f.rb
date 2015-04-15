require 'date'
require 'time'

class Gigasecond
  attr_reader :date

  def initialize(date)
    self.date = date.to_date
  end

  def self.from(date)
    new(date)
  end

  def date=(date)
    @date = Time.at(date.to_time.to_i + 10**9).to_date
  end
end
