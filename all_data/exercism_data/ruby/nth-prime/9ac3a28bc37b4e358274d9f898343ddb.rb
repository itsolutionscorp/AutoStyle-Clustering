require 'Prime'

class Prime
  
  def self.nth(n)
    raise ArgumentError, 'Please use positive integers.' unless n > 0 && (n.is_a?Integer)
    #nth_prime = 
    Prime.first(n).last
  end
  
end
