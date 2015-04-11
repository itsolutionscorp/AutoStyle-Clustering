# testing project list timing on exercism with this dummy edit
require 'date'
require 'time'

require 'delegate'
class Gigasecond < SimpleDelegator

  attr_reader :date

  def initialize(birthday)
    @date = add_gigasecond(birthday)
    super(@date)
  end

  private
  def add_gigasecond(dt)
    (dt.to_time + (10 ** 9)).to_date
  end
end
