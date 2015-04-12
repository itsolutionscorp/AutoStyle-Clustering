def compute(adn_1, adn_2)

	calculo = 0

		for i in 0..adn_1.length
			if adn_1[i] != adn_2[i]
				calculo += 1
			end
		end


	return calculo

	end