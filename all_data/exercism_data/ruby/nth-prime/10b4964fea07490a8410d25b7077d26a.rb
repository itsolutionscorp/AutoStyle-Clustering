require 'prime'

class Prime 
  def self.nth(i)
    raise ArgumentError if i < 1
    first(i).last
  end
end
