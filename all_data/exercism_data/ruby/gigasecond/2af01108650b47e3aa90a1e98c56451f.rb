require 'date'
require 'active_support'
require 'active_support/core_ext'

class Gigasecond
  GIGASECOND = 1_000_000_000.seconds

  def self.from(date)
    return (date + GIGASECOND).to_date
  end
end
