def compute(adn_1, adn_2)
		diferencia = 0

		adn_1.chars.each_with_index do |char, index|
			diferencia += 1 if char != adn_2[index]
		end 

		diferencia
	end