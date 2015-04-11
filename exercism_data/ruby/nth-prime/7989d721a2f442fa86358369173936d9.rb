require 'prime'
class Prime
  def self.nth number
    raise ArgumentError if number < 1
    first(number).last
  end
end
