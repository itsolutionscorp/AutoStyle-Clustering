require 'date'

class Gigasecond
  def self.from(startDate)
    gigasecond = 10 ** 9
    time_in_i = startDate.to_time.to_i
    Time.at(time_in_i + gigasecond).to_date
  end
end
