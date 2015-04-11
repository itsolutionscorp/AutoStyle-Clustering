require 'date'

class Gigasecond
  def self.from(birthday)
    birthday + 10**9/86400
  end
end
