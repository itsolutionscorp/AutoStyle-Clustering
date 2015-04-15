class Fixnum
	def to_roman
		romans = {0 => '', 1 => 'I', 4 => 'IV', 5 => 'V', 9 => 'IX', 10 => 'X', 50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M'}
		
		nb = self
		decomposition = []
		result = []

			if nb <= 5
				if nb == 4 or nb == 5
					result << romans[nb]
				else
					decomposition = nb.divmod(5) 
					result << "I"* decomposition[1]
					result.join
				end
 			end

			if nb > 5 && nb <= 10
					if nb == 9
						result << romans[nb]
					else
						decomposition = nb.divmod(5)
						result << "V" + "I"* decomposition[1]
						result.join
					end
	 		end

	 		if nb > 10 #&& <= 10
						decomposition = nb.divmod(10) #[2, 7]
						result << "X"* decomposition[0] + "I"* decomposition[1] #il faudrait que 7 soit décomposé !
						result.join
			end
		
		result[0]

	end

end
