require 'date'
require 'time'
class Gigasecond
  class << self
    def from(date)
      Date.strptime((Time.parse(date.to_s).to_i + 10**9).to_s, '%s')
    end
  end
end
