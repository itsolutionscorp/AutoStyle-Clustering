require 'date'
require 'time'

class Gigasecond
  attr_reader :init_date

  def initialize(init_date)
    @init_date = init_date
  end

  def date
    new_datetime = @init_date.to_time + 10**9
    new_datetime.to_date
  end
end
