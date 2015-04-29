require_relative 'food_chainsong'

class FoodChainSong

  def verse(verse_number)
    @song[verse_number-1]
  end

  def verses(verse_number1, verse_number2)
    index_1 = verse_number1 - 1
    index_2 = verse_number2 - 1

    @song[index_1..index_2].join("\n") + "\n"


    # song = []
    # (verse_number1-1..verse_number2-1).each do |index|
    #   song.push @song[index]+"\n"
    # end
    # p song.join
  end

  def sing
    verses(1, @song.length + 1)
    # @song.join("\n") + "\n"
  end

end
