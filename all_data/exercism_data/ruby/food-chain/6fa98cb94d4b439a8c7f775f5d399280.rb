class FoodChainSong

  ANIMALS = %w[ 0 fly spider bird cat dog goat cow horse ]

  def sing
    verses(1, 8)
  end

  def verses(first, last)
    verses = []
    (first..last).each { |verse| verses << verse(verse) }
    verses.join("\n") + "\n"
  end

  def verse(verse_num)
    raise ArgumentError if verse_num < 1 || verse_num > 8

    lines = ["I know an old lady who swallowed a #{ANIMALS[verse_num]}."]

    if verse_num == 8
      lines << "She's dead, of course!\n"
    else

      if verse_num >= 3
        phrases = { 3 => "How absurd to swallow ", 
                    4 => "Imagine that, to swallow ", 
                    5 => "What a hog, to swallow " , 
                    6 => "Just opened her throat and swallowed ", 
                    7 => "I don't know how she swallowed " } 
        lines << phrases[verse_num] + "a #{ANIMALS[verse_num]}!"
      end

      if verse_num >= 4
        i = verse_num
        while i >= 4
          lines << "#{swallow_catch(i)}."
          i -= 1
        end
      end

      if verse_num >= 2
        phrase = ""
        if verse_num == 2
          phrase << "It "
        else
          phrase << "#{swallow_catch(3)} that "
        end
        lines << phrase + "wriggled and jiggled and tickled inside her." 
      end

      if verse_num >= 2
        lines << "#{swallow_catch(2)}."
      end

      lines << "I don't know why she swallowed the #{ANIMALS[1]}. Perhaps she'll die.\n"
    end

    lines.join("\n")
  end

  private

    def swallow_catch(num)
      "She swallowed the #{ANIMALS[num]} to catch the #{ANIMALS[num-1]}"
    end
end
