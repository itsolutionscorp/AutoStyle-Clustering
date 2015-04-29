=begin
  File: raindrops.rb
  Author: sherinom
=end
require 'prime'

class Raindrops

  def self.convert(number)
    
	prime_factors = (1..number).select { |n| (number % n).zero? && Prime.prime?(n)}
	
	result = ''
	if prime_factors.include? 3
		result += 'Pling'
	end
	
	if prime_factors.include? 5
		result += 'Plang'
	end
	
	if prime_factors.include? 7
		result += 'Plong'
	end
	
	if result.empty?
		result = number.to_s
	end
	
	return result
	
  end

end
