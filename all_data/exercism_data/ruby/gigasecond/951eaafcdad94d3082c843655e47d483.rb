class Gigasecond
  require 'date'
  require 'time'

  def self.from(start_date)
  	Time.at(start_date.to_time + 10**9).to_date
  end
end
