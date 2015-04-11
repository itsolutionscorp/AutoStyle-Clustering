require 'mathn'

class Prime
  def self.nth(num)
    num > 0 or raise ArgumentError, "nth input cannot be less than 1"
    (Prime.first num).last
  end
end
