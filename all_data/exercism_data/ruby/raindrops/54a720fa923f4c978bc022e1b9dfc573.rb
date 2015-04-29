require 'prime'
class Raindrops
	@mappings = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong', 1 => ''}

	def self.convert(number)
		drops = @mappings.map { |prime, word|
			number.modulo(prime).zero? ? word : ''
		}.uniq.join

		drops == '' ? number.to_s : drops
	end
end
