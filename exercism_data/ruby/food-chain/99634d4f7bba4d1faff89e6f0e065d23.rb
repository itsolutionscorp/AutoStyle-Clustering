class FoodChainSong
	Song = File.open("food_chain.txt", "r").read.split("\n\n").each {|verse| verse << "\n"}
	
	def verse(first)
		Song[(first-1)]
	end

	def verses(first,last)
		verses = ""
		Song[(first-1)..(last-1)].each {|v| verses << v << "\n"}
		verses
	end

	def sing
		verses(1,8)
	end
end
