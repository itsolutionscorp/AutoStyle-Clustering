require 'date'
require 'time'

class Gigasecond
  attr_reader :date

  def initialize(date)
    @date = Time.at(date.strftime('%s').to_i + 10**9 + 100000).to_date
  end
end
