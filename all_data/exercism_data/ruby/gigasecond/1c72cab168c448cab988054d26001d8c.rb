require 'date'
require 'time'
class Gigasecond
  def self.from date
    date = Time.parse date.to_s
    (date + (10**9)).to_date
  end
end

# date = Time.parse Date.new(2011, 4, 25).to_s
# puts date
# date2 = Date.new(2043, 1, 1)
# puts "correct is #{date2}"
# puts (date + (10**9)).to_date
