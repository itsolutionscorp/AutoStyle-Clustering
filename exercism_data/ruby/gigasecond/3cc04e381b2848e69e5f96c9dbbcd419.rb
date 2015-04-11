require 'date'
require 'time'

module Gigasecond

  def self.from(date)
    date =Time.mktime(date.year, date.month, date.day)
    (date + 1_000_000_000).to_date
  end

end

puts Gigasecond.from(Date.new(1914,1,15))
