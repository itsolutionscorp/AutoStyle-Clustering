class Gigasecond
  require 'date'
  require 'time'

  ONE_GIGASECOND = 10**9

  def self.from(start_date)
  	Time.at(start_date.to_time + ONE_GIGASECOND).to_date
  end
end
