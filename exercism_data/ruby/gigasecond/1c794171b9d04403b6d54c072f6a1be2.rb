class Gigasecond
  def self.from(time)
    second = 1
    minute = second * 60
    hour = minute * 60
    day  = hour * 24
    giga = (day * 365 * 31.7) +
     day * 4 -
     hour * 11 +
     minute * 46 +
     second * 40

    time + giga
  end
end
