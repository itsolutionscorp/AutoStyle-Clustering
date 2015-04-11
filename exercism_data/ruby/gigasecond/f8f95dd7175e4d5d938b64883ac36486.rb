require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    return Time.at(10**9 + date.to_time.to_i).to_date
  end

end
