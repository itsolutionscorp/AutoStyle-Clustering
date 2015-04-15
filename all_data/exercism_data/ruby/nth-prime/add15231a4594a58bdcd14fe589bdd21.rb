require 'prime'
class Prime
  def self.nth(x)
    raise ArgumentError if x < 1 
    Prime.first(x).last
  end
end
