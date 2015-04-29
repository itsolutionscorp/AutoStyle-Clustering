require 'date'
require 'time'
class Gigasecond
  def self.from(date)
    return (date+11574)
  end
end

your_birthday = Date.new(1994, 7, 24)
    p gs = Gigasecond.from(your_birthday)
