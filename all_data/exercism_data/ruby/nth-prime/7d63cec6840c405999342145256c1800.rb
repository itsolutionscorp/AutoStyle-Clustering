require 'prime'

class Prime
  def self.nth(num)
    raise ArgumentError if num < 1
    take(num).last
  end
end
