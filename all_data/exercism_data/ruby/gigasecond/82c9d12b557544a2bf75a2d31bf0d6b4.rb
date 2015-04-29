require 'date'
require 'time'
class Gigasecond
  def self.from(date)
  	return date + Rational((1000000000-6400), 86400);
  end
end
