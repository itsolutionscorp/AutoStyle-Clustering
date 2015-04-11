#I know this is hacky, but I'd like to start
#getting feedback so I can learn ways
#I wouldn't really think of.

class Beer

	def verse n

    two = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    one = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		zero = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    
    verse = String.new
    
    if n > 2
      verse += "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{ n - 1} bottles of beer on the wall.\n"
    elsif n == 2
      verse += two
    elsif n == 1
      verse += one
    else
      verse = zero
    end
    verse
  end	

  def sing n, *i

    song = String.new

    if i[0]
      while n >= i[0]
        song += verse(n) + "\n"
        n = n - 1
      end
    else
      while n > 2
        song += verse(n) + "\n" 
        n = n - 1
      end
      song += verse(2) + "\n" + verse(1) + "\n" + verse(0) + "\n"
    end
    song

  end

end
