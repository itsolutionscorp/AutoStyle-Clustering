require 'date'

class Gigasecond
  @@days = (10**9) / 86400

  def self.from(d)
    d + @@days
  end
end
