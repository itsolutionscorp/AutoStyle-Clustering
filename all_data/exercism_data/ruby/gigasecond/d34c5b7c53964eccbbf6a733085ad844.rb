require 'date'
require 'time'

class Gigasecond
  def self.from(birthday)
    day_seconds = 60 * 60 * 24
    giga = 10 ** 9
    anniversary = birthday
    anniversary += 1 while (anniversary - birthday + 1) * day_seconds < giga
    anniversary
  end
end
