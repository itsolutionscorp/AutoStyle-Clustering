require 'date'
require 'time'

class Gigasecond
  attr_reader :date

  def initialize(date)
    @date ||= render_date(date)
  end

  def render_date(date)
    (date.to_time + one_gigasecond).to_date
  end

  def one_gigasecond
    10**9
  end
end
