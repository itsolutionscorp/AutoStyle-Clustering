class BeerSong 
	def initialize 
	end
	
	def verse(number) 
		x = number 
		y = number-1
		x = "No more" if x == 0
		y = "no more" if y == 0
		
		case 
		when number == 1
			string = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n" 
		when number == 0
			string = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
		else
			string = "#{x} bottles of beer on the wall, #{x} bottles of beer.\n"
			second = "Take one down and pass it around, #{y} bottles of beer on the wall.\n"
			if number == 2
				string << second.gsub(/bottles/, "bottle") 
			else
				string << second
			end
		end
		string
	end
	
	def verses(start, finish)
		array = start.downto(finish).map {|x| x}
		array.map!{ |x| verse(x) }
		string = array.join("\n")
		string << "\n"
	end
	
	def sing
		verses(99,0)
	end
end
