class Hamming
	def self.compute(adn_1, adn_2)
    adn_1.chars.zip(adn_2.chars).count{|x| x[0] != x[1]}
	end
end
