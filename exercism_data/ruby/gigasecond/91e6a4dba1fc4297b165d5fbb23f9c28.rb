require 'date'

class Gigasecond

  def self.from(date)
    return DateTime.strptime((date.to_time.to_i + 10 ** 9).to_s, '%s').to_date
   end

end
