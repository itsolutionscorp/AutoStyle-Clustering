require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    date_for(seconds_for(date) + 10**9)
  end

  private

  def self.seconds_for(date)
    date.to_time.to_i
  end

  def self.date_for(seconds)
    Time.at(seconds).to_date
  end
end
