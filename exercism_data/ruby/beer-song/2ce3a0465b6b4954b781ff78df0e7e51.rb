class Beer
  def verse(verse_number)
    if verse_number == 0
      no_more_beer
    elsif verse_number == 1
      one_more_beer
    elsif verse_number == 2
      two_more_beers
    else
      more_beer(verse_number)
    end
  end

  def sing(start, finish=0)
    start.downto(finish).collect { |verse_num| verse(verse_num) }.join("\n") + "\n"
  end

  private

  def no_more_beer
    <<-eos
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
    eos
  end

  def one_more_beer
    <<-eos
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
    eos
  end

  def two_more_beers
    <<-eos
2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.
    eos
  end

  def more_beer(verse_number)
    <<-eos
#{verse_number} bottles of beer on the wall, #{verse_number} bottles of beer.
Take one down and pass it around, #{verse_number - 1} bottles of beer on the wall.
    eos
  end
end
