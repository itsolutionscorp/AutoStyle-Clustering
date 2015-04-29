# Gigasecond date converter
#   Alex Standke, October 2014

class Gigasecond
  def self.from b
    (b.to_time + (10**9)).to_date
  end
end
