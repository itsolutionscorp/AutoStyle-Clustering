class Raindrops

	def self.convert(number)

		translation = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'} 

		conversion = translation.keys.inject("") do |conversion, prime| 
			number % prime == 0 ? conversion << translation[prime] : conversion 
		end 
		
		conversion.empty? ? number.to_s : conversion	
	end

	# this is less clear, has more methods and isn't faster
=begin
  def self.convert(number)

		translation = Hash.new("")
		translation.merge!({3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}) 

		conversion = translation.keys.inject("") do |conversion, prime| 
			conversion << translation[(((number % prime) + 1) * prime)] 
		end 
		
		conversion.empty? ? number.to_s : conversion	
	end
=end
end
