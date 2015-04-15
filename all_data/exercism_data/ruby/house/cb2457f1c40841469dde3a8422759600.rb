class House
	HOUSE = {
		"house that Jack built." => "lay in the house that Jack built.",
		"malt" => "ate the malt",
		"rat" => "killed the rat",
		"cat" => "worried the cat",
		"dog" => "tossed the dog",
		"cow with the crumpled horn" => "milked the cow with the crumpled horn",
		"maiden all forlorn" => "kissed the maiden all forlorn",
		"man all tattered and torn" => "married the man all tattered and torn",
		"priest all shaven and shorn" => "woke the priest all shaven and shorn",
		"rooster that crowed in the morn" => "kept the rooster that crowed in the morn",
		"farmer sowing his corn" => "belonged to the farmer sowing his corn",
		"horse and the hound and the horn" => ""

	}

	def self.recite
		result = ""
		HOUSE.keys.each_with_index do |key,i|
			result << "This is the #{key}"
			HOUSE.to_enum.with_index.reverse_each do |(k,v),index|
			       result << "\nthat #{v}" if i > index	
			end
			result << "\n"
			result << "\n" unless i == 11
		end
		result
	end
end
