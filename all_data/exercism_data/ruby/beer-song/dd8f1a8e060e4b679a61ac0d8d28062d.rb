class Beer
  def verse(num)
		if num == 0
			"No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
		elsif num == 1
			"1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		elsif num == 2
			"2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"	
		else
			num_minus = num-1
			"#{num} bottles of beer on the wall, #{num} bottles of beer.\nTake one down and pass it around, #{num_minus} bottles of beer on the wall.\n"
		end
	end

	def sing(start_num, end_num=0)
	  result=verse(start_num) + "\n"
		while start_num > end_num
			start_num -= 1
			result = result + verse(start_num) + "\n"
		end
		result
	end
end
