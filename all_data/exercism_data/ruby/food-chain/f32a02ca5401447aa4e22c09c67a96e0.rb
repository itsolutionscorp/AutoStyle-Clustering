class FoodChainSong

  def verse(num)
    lyrics = send("verse_#{num}_1")
    if num < 8
      num.downto(1) do |x|
        lyrics += send("verse_#{x}_2")
      end
    end
    lyrics
  end

  def verses(first, last)
    lyrics = ""
    first.upto(last) do |x|
      lyrics += verse(x) + "\n"
    end
    lyrics
  end

  def sing
    verses(1,8)
  end

  private

  def verse_1_1
    "I know an old lady who swallowed a fly.\n"
  end

  def verse_1_2
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verse_2_1
    "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n"
  end

  def verse_2_2
    "She swallowed the spider to catch the fly.\n"
  end

  def verse_3_1
    "I know an old lady who swallowed a bird.\n" \
    "How absurd to swallow a bird!\n"
  end

  def verse_3_2
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
  end

  def verse_4_1
    "I know an old lady who swallowed a cat.\n" \
    "Imagine that, to swallow a cat!\n"
  end

  def verse_4_2
    "She swallowed the cat to catch the bird.\n"
  end

  def verse_5_1
    "I know an old lady who swallowed a dog.\n" \
    "What a hog, to swallow a dog!\n"
  end

  def verse_5_2
    "She swallowed the dog to catch the cat.\n"
  end

  def verse_6_1
    "I know an old lady who swallowed a goat.\n" \
    "Just opened her throat and swallowed a goat!\n"
  end

  def verse_6_2
    "She swallowed the goat to catch the dog.\n"
  end

  def verse_7_1
    "I know an old lady who swallowed a cow.\n" \
    "I don't know how she swallowed a cow!\n" \
  end

  def verse_7_2
    "She swallowed the cow to catch the goat.\n"
  end

  def verse_8_1
    "I know an old lady who swallowed a horse.\n" \
    "She's dead, of course!\n"
  end
end
