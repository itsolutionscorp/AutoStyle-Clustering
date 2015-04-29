require 'mathn'

class Gigasecond
  def self.from(date)
    date = date.to_date 
    d = date + Rational(10.power!(9), 86400)
    Date.new(d.year, d.month, d.day)
  end
end
