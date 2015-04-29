require "pry"

class Gigasecond
  def self.from(date)
    conv_date = date.to_time + 1000000000
    Date.new(conv_date.year, conv_date.month, conv_date.day)
  end
end
