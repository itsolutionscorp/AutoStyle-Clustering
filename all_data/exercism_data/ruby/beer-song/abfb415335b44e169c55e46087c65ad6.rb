#I know this is hacky, but I'd like to start
#getting feedback so I can learn ways
#I wouldn't really think of.

class Beer

	def verse n

    many = "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{ n - 1} bottles of beer on the wall.\n"
    two = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    one = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
		zero = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    
    if n > 2
      many
    elsif n == 2
      two
    elsif n == 1
      one
    else
      zero
    end

  end	

  def sing n, i = 0

    song = String.new

    n.downto(i) do |n|
      song += verse(n) + "\n"
    end

    song
  
  end

end
