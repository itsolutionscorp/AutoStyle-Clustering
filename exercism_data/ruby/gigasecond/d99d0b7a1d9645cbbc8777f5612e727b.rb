require 'active_support/core_ext'

class Gigasecond
  def self.from(datetime = Datetime.now)
    datetime + 1000000000.seconds
  end
end
