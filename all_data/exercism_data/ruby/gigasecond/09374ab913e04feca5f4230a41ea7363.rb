require 'date'
require 'active_support'
require 'active_support/core_ext'

class Gigasecond
  def self.from(date)
    return (date + (10**9).seconds).to_date
  end
end
