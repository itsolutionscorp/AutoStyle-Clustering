require 'date'

# Get date 1 billion seconds in the future
class Gigasecond
  def self.from(date)
    date + (10**9 / 60 / 60 / 24)
  end
end
