# Write a program that outputs the lyrics of the song 'I Know an Old Lady Who Swallowed a Fly'

# This is a [cumulative song](http://en.wikipedia.org/wiki/Cumulative_song) of unknown origin.

# This is one of many common variants.

# ```plain
# I know an old lady who swallowed a fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a spider.
# It wriggled and jiggled and tickled inside her.
# She swallowed the spider to catch the fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a bird.
# How absurd to swallow a bird!
# She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
# She swallowed the spider to catch the fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a cat.
# Imagine that, to swallow a cat!
# She swallowed the cat to catch the bird.
# She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
# She swallowed the spider to catch the fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a dog.
# What a hog, to swallow a dog!
# She swallowed the dog to catch the cat.
# She swallowed the cat to catch the bird.
# She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
# She swallowed the spider to catch the fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a goat.
# Just opened her throat and swallowed a goat!
# She swallowed the goat to catch the dog.
# She swallowed the dog to catch the cat.
# She swallowed the cat to catch the bird.
# She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
# She swallowed the spider to catch the fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a cow.
# I don't know how she swallowed a cow!
# She swallowed the cow to catch the goat.
# She swallowed the goat to catch the dog.
# She swallowed the dog to catch the cat.
# She swallowed the cat to catch the bird.
# She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.
# She swallowed the spider to catch the fly.
# I don't know why she swallowed the fly. Perhaps she'll die.

# I know an old lady who swallowed a horse.
# She's dead, of course!
# ```

class FoodChainSong

  def verse(num)
    verses = 
      {
      1 => "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n",
      2 => "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      3 => "I know an old lady who swallowed a bird.\n" \
      "How absurd to swallow a bird!\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n",
      4 => "I know an old lady who swallowed a cat.\n" \
        "Imagine that, to swallow a cat!\n" \
        "She swallowed the cat to catch the bird.\n" \
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
        "She swallowed the spider to catch the fly.\n" \
        "I don't know why she swallowed the fly. " \
        "Perhaps she'll die.\n",
      5 => "I know an old lady who swallowed a dog.\n" \
      "What a hog, to swallow a dog!\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",

      6 => "I know an old lady who swallowed a goat.\n" \
      "Just opened her throat and swallowed a goat!\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",

      7 => "I know an old lady who swallowed a cow.\n" \
      "I don't know how she swallowed a cow!\n" \
      "She swallowed the cow to catch the goat.\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n",

      8 => "I know an old lady who swallowed a horse.\n" \
      "She's dead, of course!\n"

    }

    verses[num]
  end

  def verses(first, last)
    lines = [*(first..last)]
    lines.map{|l| verse(l) + "\n" }.join
  end

  def sing
    verses(1, 8)
  end
end

v = FoodChainSong.new
print v.verses(1,2)
