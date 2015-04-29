require 'date'
require 'time'

class Gigasecond

  class << self
    def from(date_object)
      given = date_object.to_time + 10**9
      future = given.to_date
    end
  end
end
