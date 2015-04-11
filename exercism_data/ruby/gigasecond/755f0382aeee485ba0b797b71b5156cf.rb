require 'date'
require 'time'

class Gigasecond
  def self.from(born)
    q = (born + Rational(10**9, 60 * 60 * 24))
    DateTime.new(q.year, q.month, q.day, 0, 0, 0, 0)
  end
end
