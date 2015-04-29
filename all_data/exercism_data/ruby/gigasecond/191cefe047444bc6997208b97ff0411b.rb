require 'date'

class Gigasecond

  GIGASECOND_DAYS = (((1_000_000_000 / 60) / 60) / 24)

  def self.from birthday
    birthday + GIGASECOND_DAYS
  end

end
