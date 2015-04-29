require 'date'

class Gigasecond
  def self.from(birthday)
    t = birthday.to_time
    t += 1e9
    t.to_date
	end
end
