class Gigasecond
  require 'Date'
  def self.from(date)
    date = date.class == "Date" ? date : Date.parse(date.strftime('%Y/%m/%d'))
    days = (10**9)/60/60/24
    date + days
  end

end
