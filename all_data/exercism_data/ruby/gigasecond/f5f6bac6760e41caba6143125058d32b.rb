require 'time'

class Gigasecond
  def self.from(moment)
    moment = moment.to_time unless moment.is_a? Time
    moment += 10**9
    moment.to_date
  end
end
