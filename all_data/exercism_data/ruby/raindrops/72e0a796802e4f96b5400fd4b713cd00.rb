class Raindrops
       PRIMES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }	
  class << self 
    def convert(number)
	    range = *(1..number)
	    factors = range.delete_if{|x| number % x != 0}
	    result = PRIMES.select {|k,v| factors.include?(k) }.values.join

	    if result.empty? 
		    number.to_s
	    else
		   result 
	    end
    end    
  end
end
