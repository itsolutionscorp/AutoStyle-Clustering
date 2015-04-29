# A gigasecond is one billion (10**9) seconds
require 'date'


class Gigasecond
  @@days = (10**9) / 3600 / 24

  def self.from(date)
    if date.class==Date
      date+@@days
    elsif date.class==Time
      result=date+(10**9)
      Date.new(result.year, result.month, result.day)
    end
  end

end
