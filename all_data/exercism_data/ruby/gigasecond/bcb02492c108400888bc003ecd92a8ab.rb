require "date"
require "time"

class Gigasecond
  def self.from(arg1)
    arg1.to_i + 10**9
    Time.at(arg1.to_i + 10**9)
  end
end
