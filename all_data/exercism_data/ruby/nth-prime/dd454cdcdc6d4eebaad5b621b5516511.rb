require 'prime'

class Prime
    
    def self.nth( n )
        Prime.instance.nth n
    end
    
    def nth( n )
        raise ArgumentError.new( "n must be a positive integer" ) if n <= 0 or !n.is_a? Integer
        first( n ).last
    end
  
end
