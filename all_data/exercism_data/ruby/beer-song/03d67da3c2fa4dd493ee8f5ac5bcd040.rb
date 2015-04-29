class BeerSong

	def initialize
		@song = ''
	end
	
	BOB = 'bottles of beer'
	BOS = 'bottle of beer'
	OTW = 'on the wall'
	TOD = 'Take one down' 
	PIA = 'and pass it around'
	
	def verse(number)
		if number > 2
			"#{number} #{BOB} #{OTW}, #{number} #{BOB}.\n#{TOD} #{PIA}, #{number - 1} #{BOB} #{OTW}.\n"
		elsif number == 2
			"#{number} #{BOB} #{OTW}, #{number} #{BOB}.\n#{TOD} #{PIA}, #{number - 1} bottle of beer #{OTW}.\n"
		elsif number == 1
			"1 #{BOS} #{OTW}, 1 #{BOS}.\nTake it down #{PIA}, no more #{BOB} #{OTW}.\n"
		else
			"No more #{BOB} #{OTW}, no more #{BOB}.\nGo to the store and buy some more, 99 #{BOB} #{OTW}.\n"
		end
	end
	
	def verses(from, to)
		verses = ''
		from.downto(to) { |n| verses = verses + verse(n) +"\n" }
		verses
	end
	
	def sing
		99.downto(0) { |n| @song = @song + verse(n) + "\n"}
		@song 
	end
end
