require 'time'

class Gigasecond
  def self.from(time_obj)
    time_obj + 10**9
  end
end
