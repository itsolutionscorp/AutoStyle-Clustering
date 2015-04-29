require 'date'

class Gigasecond

  def initialize(input)
    @date = input
  end

  def date
    @date + time_to_add
  end

  def time_to_add
    gigasecond = 10 ** 9

    second = 1
    minute = second * 60
    hour = minute * 60
    day = hour * 24
    month = day * 30
    year = (month * 12) + (day*5)

    times = [[year, 365], [month, 30], [day, 1]]

    x = 0
    days_to_add = 0

    while gigasecond > day
      if gigasecond >= times[x][0]
        gigasecond -= times[x][0]
        days_to_add += times[x][1]
      else
        x += 1
      end
    end
    days_to_add
  end

end
