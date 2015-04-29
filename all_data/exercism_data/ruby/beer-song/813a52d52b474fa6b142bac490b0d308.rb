class Beer
	def verse(num)
		if num==0
			"No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
		elsif num==1
			"1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		elsif num==2
		 "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"	
		else
			num_minus=num-1
			"#{num} bottles of beer on the wall, #{num} bottles of beer.\nTake one down and pass it around, #{num_minus} bottles of beer on the wall.\n"
		end
	end

	def sing(num1, num2=0)
		if @result.nil?
			@result=verse(num1)+"\n"
		end
		while num1 > num2
			num1-=1
			@result=@result+verse(num1)+"\n"
		end
		@result
	end


end
