class Raindrops
  def self.convert(number)
    print "Pling" if (number % 3 ).zero?  
    print "Plang" if number.modulo(5).zero?  
    print "Plong" if number.modulo(7).zero?  
    number.to_s if number.modulo(3).nonzero? && number.modulo(5).nonzero? && number.modulo(7).nonzero?
  end
end
