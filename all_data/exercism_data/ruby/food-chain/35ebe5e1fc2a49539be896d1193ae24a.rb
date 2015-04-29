class FoodChainSong

  def initialize
    @string = ""
  end
  def multiVerse(x)
    if x >= 7
      @string << "I don't know how she swallowed a cow!\n" +
      "She swallowed the cow to catch the goat.\n" +
      "She swallowed the goat to catch the dog.\n" +
      "She swallowed the dog to catch the cat.\n" +
      "She swallowed the cat to catch the bird.\n" +
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      "She swallowed the spider to catch the fly.\n"
    elsif x >= 6
      @string << "Just opened her throat and swallowed a goat!\n" +
      "She swallowed the goat to catch the dog.\n" +
      "She swallowed the dog to catch the cat.\n" +
      "She swallowed the cat to catch the bird.\n" +
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      "She swallowed the spider to catch the fly.\n"
    elsif x >= 5
      @string << "What a hog, to swallow a dog!\n" +
      "She swallowed the dog to catch the cat.\n" +
      "She swallowed the cat to catch the bird.\n" +
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      "She swallowed the spider to catch the fly.\n"
    elsif x >= 4
      @string << "Imagine that, to swallow a cat!\n" +
      "She swallowed the cat to catch the bird.\n" +
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      "She swallowed the spider to catch the fly.\n"
    elsif x >= 3
      @string << "How absurd to swallow a bird!\n" +
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
      "She swallowed the spider to catch the fly.\n"
    elsif x >= 2
      @string << "It wriggled and jiggled and tickled inside her.\nShe swallowed the spider to catch the fly.\n"
    end
  end

  def sing
    verses(1, 8)
  end

  def verses(*n)
    animals = ["fly", "spider", "bird", "cat", "dog", "goat", "cow"]
    n.each{|x|
    if x >= 8
      return "I know an old lady who swallowed a horse.\n" +
      "She's dead, of course!\n"
    else
      @string << "I know an old lady who swallowed a #{animals[x-1]}.\n"
      multiVerse(x)
      @string << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
    @string << "\n"
    }
    @string
  end

  def verse(*n)

    animals = ["fly", "spider", "bird", "cat", "dog", "goat", "cow"]
    n.each{|x|
    if x >= 8
      return "I know an old lady who swallowed a horse.\n" +
      "She's dead, of course!\n"
    else
      @string = "I know an old lady who swallowed a #{animals[x-1]}.\n"
      multiVerse(x)
      @string << "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      end
    }
    @string
  end
end

song = FoodChainSong.new
p song.verses(1, 2)
