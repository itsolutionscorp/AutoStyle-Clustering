require 'prime'

class Prime
  def self.nth(place)
    raise ArgumentError if place < 1

    (Prime.first place).last
  end
end
