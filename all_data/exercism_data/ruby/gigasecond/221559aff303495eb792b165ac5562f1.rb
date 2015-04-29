require 'date'
require 'time'

class Gigasecond
  # 1 Gigasecond = 1 billion seconds
  attr_accessor :birthday
  def initialize(birthday = Date.now)
    @birthday = birthday
  end

  def date
    #t = birthday.to_time + 1_000_000_000
    #Date.new(t.year, t.month, t.day)
    (birthday.to_time + 1_000_000_000).to_date
  end

end
