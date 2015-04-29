class BeerSong

	def initialize
		@beers = 0
	end

	def verse(beers)
		@beers = beers

		song = "#{number_of_bottles} of beer on the wall, #{number_of_bottles.downcase} of beer.\n#{take_or_buy_more}, "
		@beers -= 1
		song += "#{number_of_bottles.downcase} of beer on the wall.\n"
		return song
	end

	def verses(start, stop)
		song = ""
		start.downto(stop) {|x| song += verse(x) + "\n"}
		return song
	end

	def sing
		verses(99,0)
	end


	private

	def number_of_bottles
		bottle_number + " " + word_for_bottles
	end

	def bottle_number
		@beers > 0 ? @beers.to_s : "No more"
	end

	def word_for_bottles
		@beers == 1 ? "bottle" : "bottles"
	end

	def article
		@beers == 1 ? "it" : "one"
	end

	def take_or_buy_more
		 if @beers == 0
		 	@beers = 100
		 	return "Go to the store and buy some more"
		 end

		 return "Take #{article} down and pass it around"
	end

end
