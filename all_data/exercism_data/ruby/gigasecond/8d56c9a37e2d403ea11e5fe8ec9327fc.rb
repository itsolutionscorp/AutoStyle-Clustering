class Gigasecond
  require 'date'

  def self.from(date_time)
    add_one_gigasecond(date_time.to_time).to_date
  end

  def self.seconds_per_gigasecond
    10**9
  end

  def self.add_one_gigasecond(time)
    time + seconds_per_gigasecond
  end
end
