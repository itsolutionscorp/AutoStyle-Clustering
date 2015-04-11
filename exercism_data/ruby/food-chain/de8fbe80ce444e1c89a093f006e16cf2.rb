class FoodChainSong

  ANIMALS = { 1 => ['fly', ''],
              2 => ['spider', "It wriggled and jiggled and tickled inside her.\n"], 
              3 => ['bird', "How absurd to swallow a bird!\n"],
              4 => ['cat', "Imagine that, to swallow a cat!\n"],
              5 => ['dog', "What a hog, to swallow a dog!\n"],
              6 => ['goat', "Just opened her throat and swallowed a goat!\n"],
              7 => ['cow', "I don't know how she swallowed a cow!\n"],
              8 => ['horse', "She's dead, of course!\n"]
            }

  def verse(num)
    animal, unique_phrase = ANIMALS[num]

    # unique lines
    lyrics = "I know an old lady who swallowed a #{animal}.\n"
    lyrics << unique_phrase
    return lyrics if num == 8 # last verse is short

    # repeating lines
    num.downto(2) do |i|
      lyrics << "She swallowed the #{ANIMALS[i][0]} to catch the #{ANIMALS[i-1][0]}"
      lyrics << " that wriggled and jiggled and tickled inside her" if ANIMALS[i][0] == "bird"
      lyrics << ".\n"
    end

    lyrics <<"I don't know why she swallowed the fly. Perhaps she'll die.\n"
    lyrics
  end

  def verses(v1, v2)
    (v1..v2).inject("") { |verses, num| verses << verse(num) + "\n" }
  end

  def sing
    verses(1,8)
  end

end



puts FoodChainSong.new.sing
