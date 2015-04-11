# Gigasecond date converter
#   Alex Standke, October 2014

class Gigasecond
  def self.from b
    (Time.new(b.year, b.month, b.day) + (10**9)).to_date
  end
end
