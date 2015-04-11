class House
	@@objects = [
								"the house that Jack built",
								"the malt that lay in",
								"the rat that ate",
								"the cat that killed",
								"the dog that worried",
								"the cow with the crumpled horn that tossed",
								"the maiden all forlorn that milked",
								"the man all tattered and torn that kissed",
								"the priest all shaven and shorn that married",
								"the rooster that crowed in the morn that woke",
								"the farmer sowing his corn that kept",
								"the horse and the hound and the horn that belonged to",
							]

	# builds a string for the number-th verse of the house poem at
  def verse(number)
		String.new << 

		"This is" <<

		(0..(number-1)).to_a.reverse.each_with_object("") do |num, str|
			str << " #{@@objects[num]}" 
		end <<

		".\n"
	end

	# builds a string for the poem from the first to the last given verses
	def verses(first, last)
		(first..last).each_with_object("") do |num, str| 
			str << verse(num) + "\n"
		end
	end
end	
