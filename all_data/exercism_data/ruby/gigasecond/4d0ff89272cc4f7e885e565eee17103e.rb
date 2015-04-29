require 'date'
require 'time'

class Gigasecond
  def self.from(date)
    days_to_go = 10**9 / (60*60*24)
    $stderr.puts "days_to_go is #{days_to_go}"
    date + days_to_go
  end
end
