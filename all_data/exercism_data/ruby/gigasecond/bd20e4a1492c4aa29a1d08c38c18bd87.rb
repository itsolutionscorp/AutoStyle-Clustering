require 'date'

class Gigasecond

  def self.from(birthday)
    return birthday + 10**9/(24*3600)
  end

end
