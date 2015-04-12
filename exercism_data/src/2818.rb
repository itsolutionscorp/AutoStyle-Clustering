def compute(adn_1, adn_2)
    adn_1.chars.zip(adn_2.chars).count{|x, i| x != i}
	end