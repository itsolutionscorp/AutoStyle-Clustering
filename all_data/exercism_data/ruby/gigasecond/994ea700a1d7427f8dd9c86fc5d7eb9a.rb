require 'date'

class Gigasecond
  def Gigasecond.from (date)
    date += (10**9 / 60 / 60 / 24)
  end
end
