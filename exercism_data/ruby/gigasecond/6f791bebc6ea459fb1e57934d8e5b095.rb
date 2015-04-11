require 'active_support/all'
require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    return date + ((10**9) / 60 /60 / 24)

  end
end
