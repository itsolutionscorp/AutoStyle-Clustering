class Robot
	def name
		@name ||= "#{prefix}#{suffix}"
	end

	def reset
		@name = nil
	end

	private

	def prefix
		alphabet.sample(2).join('')
	end

	def suffix
		3.times.map{ rand(0..9) }.join
	end

	def alphabet
		('A'..'Z').to_a
	end
end
