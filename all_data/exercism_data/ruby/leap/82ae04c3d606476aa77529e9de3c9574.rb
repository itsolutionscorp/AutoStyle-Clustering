module Year
	extend self

	def leap?(year)
		return case
			when year.modulo(400).zero?	then true
			when year.modulo(100).zero?	then false
			when year.modulo(4).zero?   then true
			else false
		end
	end
end
