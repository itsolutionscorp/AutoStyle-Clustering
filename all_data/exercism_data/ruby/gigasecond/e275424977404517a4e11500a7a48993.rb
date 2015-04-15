require 'date'  # => true

class Gigasecond

  def self.from(birthdate)
    days        = (10**9)/60/60/24  # => 11574
    anniversary = birthdate + days  # => #<Date: 2043-01-01 ((2467251j,0s,0n),+0s,2299161j)>
  end
end
