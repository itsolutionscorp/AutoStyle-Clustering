class BeerSong

  def initialize
  	@song = ''
  end

  def amount(num)
  	if num == 0
  		"No more bottles"
  	elsif num == 1
  		"#{num} bottle"
  	else
  		"#{num} bottles"
  	end
  end

  def take(num)
  	if num == 0 
  		"Go to the store and buy some more," 
  	elsif num == 1
  		"Take it down and pass it around,"
  	else
  		"Take one down and pass it around,"
  	end
  end

  def verse(num)
  	"#{amount(num)} of beer on the wall, #{amount(num).downcase} of beer.\n#{take(num)} #{num == 0 ? '99 bottles' : amount(num-1).downcase} of beer on the wall.\n"
  end

  def verses(num1, num2)
  	num1.downto(num2) {|x| @song << verse(x)+"\n"}
  	@song
  end

  def sing
    verses(99,0)
  end

end
