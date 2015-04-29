require 'date'

class Gigasecond
  def Gigasecond.from(date)
    (date.to_time + 1e9).to_date
  end
end

p  Gigasecond.from(Date.new(2011, 4, 25))
p Date.new(2043, 1, 1)
