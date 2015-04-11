require 'date'
require 'time'
class Gigasecond
  def self.from(date)
  	time=date + Rational((1000000000-6400), 86400);
  	return time;
  end
end
