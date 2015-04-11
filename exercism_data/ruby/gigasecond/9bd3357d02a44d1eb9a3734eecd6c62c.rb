require 'date'

class Gigasecond
  def anneversary(year, month, day)
    puts Date.new(year, month, day).to_time + 10**9
  end

  def time_in_seconds(year, month, day, hour, minute, second)
    puts Time.new(year, month, day, hour, minute, second) + 10**9
  end
end

bob = Gigasecond.new
bob.anneversary(1986, 9, 25)
bob.time_in_seconds(1980, 9, 25, 2, 3, 34)
