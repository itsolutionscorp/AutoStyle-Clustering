class Gigasecond
  require 'date'
  require 'time'

  GIGA_SECOND = 10**9

  def self.from(input_Date)
    gs_anniversary_in_seconds = input_Date.to_time + GIGA_SECOND
    gs_anniversary_as_Date= gs_anniversary_in_seconds.to_time.to_date
  end

end
