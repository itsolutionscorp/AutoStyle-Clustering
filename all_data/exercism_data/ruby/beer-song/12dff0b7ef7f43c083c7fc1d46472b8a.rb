class BeerSong
  def verse(verse_num)
    if verse_num == 0 then  
      return %{No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
}
    end

    one = "one"
    if verse_num == 1
      before_bottles = "1 bottle"
      after_bottles = "no more bottles"
      one = "it"
    elsif verse_num == 2
      before_bottles = "2 bottles"
      after_bottles = "1 bottle"
    else
      before_bottles = "#{verse_num} bottles"
      after_bottles = "#{verse_num - 1} bottles"
    end
    
    %{#{before_bottles} of beer on the wall, #{before_bottles} of beer.
Take #{one} down and pass it around, #{after_bottles} of beer on the wall.
}
  end

  def verses(start, finish)
    start.downto(finish).inject('') do |song, verse_num|
      song << verse(verse_num) << "\n"
    end
  end

  def sing
    verses(99, 0)
  end
end
