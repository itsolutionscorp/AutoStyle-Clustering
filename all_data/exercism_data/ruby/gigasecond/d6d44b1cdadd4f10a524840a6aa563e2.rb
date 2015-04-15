require 'date'

class Gigasecond
  def self.from(date)
    date = date.to_time
    date += 10 ** 9
    date.to_date
  end
end
