class Gigasecond
  require 'date'

  def self.from(date_time)
    start_time = date_time.to_time
    (start_time + 10**9).to_date
  end
end
