require 'prime'

class Prime
  def self.nth(which)
    raise ArgumentError if which < 1
    Prime.first(which).last    
  end
end
