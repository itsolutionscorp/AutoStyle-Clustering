class Beer

  def verse(number)
    song[number].sing(number)
  end

  def sing(start_at, end_at=0)
    verses(start_at, end_at).join("\n") << "\n"
  end

  private

  def song
    song = Hash.new(Verse.new)
    song[2] = AntepenultimateVerse.new
    song[1] = PenultimateVerse.new
    song[0] = LastVerse.new
    song
  end

  def verses(start_at, end_at)
    start_at.downto(end_at).map { |number| verse(number) }
  end

  class Verse
    attr_reader :verse_number

    def sing(verse_number)
      @verse_number = verse_number
      phrases.join
    end

    def phrases
      ["#{bottles} bottles of beer on the wall, ",
       "#{bottles} bottles of beer.\n",
       "Take one down and pass it around, ",
       "#{bottles - 1} bottles of beer on the wall.\n"]
    end

    def bottles
      verse_number
    end
  end

  class AntepenultimateVerse < Verse
    def phrases
      ["2 bottles of beer on the wall, ",
       "2 bottles of beer.\n",
       "Take one down and pass it around, ",
       "1 bottle of beer on the wall.\n"]
    end
  end

  class PenultimateVerse < Verse
    def phrases
      ["1 bottle of beer on the wall, ",
       "1 bottle of beer.\n",
       "Take it down and pass it around, ",
       "no more bottles of beer on the wall.\n"]
    end
  end

  class LastVerse < Verse
    def phrases
      ["No more bottles of beer on the wall, ",
       "no more bottles of beer.\n", 
       "Go to the store and buy some more, ",
       "99 bottles of beer on the wall.\n"]
    end
  end

end
