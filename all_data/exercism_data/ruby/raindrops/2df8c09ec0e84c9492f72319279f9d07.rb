#*****
#    Raindrops:  Convert a number to string based upon contents of numbers
#    prime factors.
#*****
class Raindrops
	
	def self.convert(number)
		response = { 3 => 'Pling', 5=> 'Plang', 7=> 'Plong' }
		response_to_string =''

		array_of_factors = generate_factors(number)
	    array_of_factors.uniq!
	 
	 	if (array_of_factors & response.keys).empty?
	 		return number.to_s
		
		else
		    array_of_factors.each { |prime| response_to_string += "#{response[prime]}" }
		    return response_to_string
	    end
	
	end

	def self.generate_factors(convert_number)
		
		return[] if convert_number == 1
		factor = (2..convert_number).find { |x| convert_number % x == 0}
		[factor] + generate_factors(convert_number / factor)	
	end
end
