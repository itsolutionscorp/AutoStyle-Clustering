class Raindrops

	def self.convert(number)
		raindrop_speak = ""
		factorization_hash = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
		factorization_hash.each do |prime_factor, output|
			raindrop_speak << output if number % prime_factor.to_i == 0
		end
		raindrop_speak << number.to_s if raindrop_speak.empty?
		raindrop_speak
	end

end
