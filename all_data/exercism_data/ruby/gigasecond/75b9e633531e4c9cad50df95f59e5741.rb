require 'time'
require 'date'

class Gigasecond
  attr_reader :date

  def initialize(date)
    date = Date.parse(date) unless date.respond_to?(:to_time)
    @date = (date.to_time + 10 ** 9).to_date
  end
end
