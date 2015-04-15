class FoodChainSong
  LYRICS = {
    1 => {
      :food         => "fly",
      :unique_part  => "",
      :contribution => "I don't know why she swallowed the fly. Perhaps she'll die.\n",
    },

    2 => {
      :food         => "spider",
      :unique_part  => "It wriggled and jiggled and tickled inside her.\n",
      :contribution => "She swallowed the spider to catch the fly.\n",
    },

    3 => {
      :food         => "bird",
      :unique_part  => "How absurd to swallow a bird!\n",
      :contribution => "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
    },

    4 => {
      :food         => "cat",
      :unique_part  => "Imagine that, to swallow a cat!\n",
      :contribution => "She swallowed the cat to catch the bird.\n",
    },

    5 => {
      :food         => "dog",
      :unique_part  => "What a hog, to swallow a dog!\n",
      :contribution => "She swallowed the dog to catch the cat.\n",
    },

    6 => {
      :food         => "goat",
      :unique_part  => "Just opened her throat and swallowed a goat!\n",
      :contribution => "She swallowed the goat to catch the dog.\n",
    },

    7 => {
      :food         => "cow",
      :unique_part  => "I don't know how she swallowed a cow!\n",
      :contribution => "She swallowed the cow to catch the goat.\n",
    },

    8 => {
      :food         => "horse",
      :unique_part  => "She's dead, of course!\n",
      :contribution => "",
    }
  }

  def verse number
    opening_part_for_verse(number) +
    unique_part_for_verse(number)  +
    accumulated_part_for_verse(number)
  end

  def verses start_number, end_number
    (start_number..end_number).each_with_object("") do |number, acc|
      acc << verse(number) + "\n"
    end
  end

  def sing
    verses 1, last_verse_number
  end

  def lyrics
    FoodChainSong::LYRICS
  end

  def last_verse_number
    lyrics.size
  end

  def opening_part_for_verse number
    "I know an old lady who swallowed a #{lyrics[number][:food]}.\n"
  end

  def unique_part_for_verse number
    lyrics[number][:unique_part]
  end

  def accumulated_part_for_verse number
    if number != last_verse_number
      contributions_up_to_lyric number
    else
      ""
    end
  end

  def contributions_up_to_lyric number
    number.downto(1).each_with_object("") do |lyric_number, acc|
      acc << lyrics[lyric_number][:contribution]
    end
  end
end
