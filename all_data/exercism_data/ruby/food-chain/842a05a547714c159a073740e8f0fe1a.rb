class FoodChainSong

  def initialize
    @lyrics = { 1 =>  "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n",
                2 =>  "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" +
                      "She swallowed the spider to catch the fly.\n" +
                      "I don't know why she swallowed the fly. Perhaps she'll die.\n",
                3 =>  "I know an old lady who swallowed a bird.\n" +
                      "How absurd to swallow a bird!\n" +
                      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
                      "She swallowed the spider to catch the fly.\n" +
                      "I don't know why she swallowed the fly. Perhaps she'll die.\n",
                4 =>  "I know an old lady who swallowed a cat.\n" +
                      "Imagine that, to swallow a cat!\n" +
                      "She swallowed the cat to catch the bird.\n" +
                      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
                      "She swallowed the spider to catch the fly.\n" +
                      "I don't know why she swallowed the fly. " +
                      "Perhaps she'll die.\n",
                5 =>  "I know an old lady who swallowed a dog.\n" +
                      "What a hog, to swallow a dog!\n" +
                      "She swallowed the dog to catch the cat.\n" +
                      "She swallowed the cat to catch the bird.\n" +
                      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
                      "She swallowed the spider to catch the fly.\n" +
                      "I don't know why she swallowed the fly. " +
                      "Perhaps she'll die.\n",
                6 =>  "I know an old lady who swallowed a goat.\n" +
                      "Just opened her throat and swallowed a goat!\n" +
                      "She swallowed the goat to catch the dog.\n" +
                      "She swallowed the dog to catch the cat.\n" +
                      "She swallowed the cat to catch the bird.\n" +
                      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
                      "She swallowed the spider to catch the fly.\n" +
                      "I don't know why she swallowed the fly. " +
                      "Perhaps she'll die.\n",
                7 =>  "I know an old lady who swallowed a cow.\n" +
                      "I don't know how she swallowed a cow!\n" +
                      "She swallowed the cow to catch the goat.\n" +
                      "She swallowed the goat to catch the dog.\n" +
                      "She swallowed the dog to catch the cat.\n" +
                      "She swallowed the cat to catch the bird.\n" +
                      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
                      "She swallowed the spider to catch the fly.\n" +
                      "I don't know why she swallowed the fly. " +
                      "Perhaps she'll die.\n",
                8 =>  "I know an old lady who swallowed a horse.\n" +
                      "She's dead, of course!\n"}
  end

  def verse(num)
    x = @lyrics[num]
  end

  def verses(first_verse, last_verse)
    @song = ""
    (first_verse..last_verse).map {|x| @song << @lyrics[x] + "\n"}
    return @song
  end

  def sing
    x = verses(1, 8)
  end
end
