class Hamming
	def compute (adn1, adn2)
		distance = 0
		if adn1.length != adn2.length
	    	return 0
	    end
	    for i in 0..adn1.length
      		if adn1[i] != adn2[i]
        		sum +=1
   			end
    	end
    	return distance
	end
end
