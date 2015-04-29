require 'prime'

class Prime 
  def self.nth(n)
    raise ArgumentError, 'Argument has to be larger then 0' unless n > 0
    Prime.first(n).last
  end
end
