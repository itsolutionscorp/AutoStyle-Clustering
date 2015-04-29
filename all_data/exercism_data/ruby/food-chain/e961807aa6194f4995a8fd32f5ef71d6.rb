class FoodChainSong
  def initialize
    @lyrics = {
      1 => {:subject => "fly", :result => "I don't know why she swallowed the fly. Perhaps she'll die.\n"},
      2 => {:subject => "spider", :result => "It wriggled and jiggled and tickled inside her.\n"},
      3 => {:subject => "bird", :result => "How absurd to swallow a bird!\n"},
      4 => {:subject => "cat", :result => "Imagine that, to swallow a cat!\n"},
      5 => {:subject => "dog", :result => "What a hog, to swallow a dog!\n"},
      6 => {:subject => "goat", :result => "Just opened her throat and swallowed a goat!\n"},
      7 => {:subject => "cow", :result => "I don't know how she swallowed a cow!\n"},
      8 => {:subject => "horse", :result => "She's dead, of course!\n"}
    }
  end

  def verse(verse_number)
    verse = "I know an old lady who swallowed a #{@lyrics[verse_number][:subject]}.\n"
    verse << "#{@lyrics[verse_number][:result]}"

    if verse_number > 1 && verse_number < 8
      verse_number.downto(2).each do |current_verse|
        verse << "She swallowed the #{@lyrics[current_verse][:subject]} "
        verse << "to catch the #{@lyrics[current_verse-1][:subject]}"
        if current_verse == 3
          verse << " #{@lyrics[current_verse-1][:result]}".gsub("It","that")
        else
          verse << ".\n"
        end
      end
      verse << @lyrics[1][:result]
    end
    # puts "start:\n#{verse}"
    verse
  end

  def verses(start,finish)
    song = ''
    (start..finish).each do |verse_number|
      song << "#{verse(verse_number)}\n"
    end
    song
  end

  def sing
    verses(@lyrics.keys[0],@lyrics.keys.last)
  end

end
