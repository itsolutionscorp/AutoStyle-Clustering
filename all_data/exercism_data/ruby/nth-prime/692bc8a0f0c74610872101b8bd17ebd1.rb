require 'prime'

class Prime
  def self.nth(number)
    raise ArgumentError if number.zero?
    Prime.first(number).last
  end
end
