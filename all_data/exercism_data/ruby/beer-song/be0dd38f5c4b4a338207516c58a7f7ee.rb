class Beer
	def setup
		@beer = beer
	end

    def sing(start, finish)
        verses = []
        start.downto(finish) do |number|
            verses << verse(number) + "\n"
        end
        verses.join
    end

	def verse(number)
        answer_bottles = "#{number - 1} bottles"
        number_bottles = "#{number} bottles"
        it = "one"
      
        if number == 2
            answer_bottles = "1 bottle"
             return "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake #{it} down and pass it around, #{answer_bottles} of beer on the wall.\n"
        end 

        if number == 8
            number_bottles = "8 bottles"
             return "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake #{it} down and pass it around, #{answer_bottles} of beer on the wall.\n"
        end

        if number == 1
            answer_bottles = "no more bottles"
            number_bottles = "1 bottle"
            it = "it"
             return "#{number_bottles} of beer on the wall, #{number_bottles} of beer.\nTake #{it} down and pass it around, #{answer_bottles} of beer on the wall.\n"
        end 

        if number == 0
            number_bottles = "No more bottles"

            return "#{number_bottles} of beer on the wall, #{number_bottles.downcase} of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
        end
    end     
end
