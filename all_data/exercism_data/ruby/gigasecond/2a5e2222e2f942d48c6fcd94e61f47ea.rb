# require 'date'
class Gigasecond

  attr_accessor :date

  SECONDS = 10**9

  def initialize(date)
    @date = date
  end

  def self.from(date)
    self.new((date.to_time + SECONDS).to_date)
  end
end
