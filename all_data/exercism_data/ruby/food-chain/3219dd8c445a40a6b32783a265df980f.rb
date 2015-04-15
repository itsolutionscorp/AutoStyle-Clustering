class FoodChainSong
  def initialize
    @swallowed = 'I know an old lady who swallowed a '
    @why = "I don\'t know why she swallowed the fly. Perhaps she\'ll die.\n"
    @wriggled = " wriggled and jiggled and tickled inside her.\n"
    @catchFly = "She swallowed the spider to catch the fly.\n"
    @bird = "She swallowed the bird to catch the spider that" + @wriggled
    @cat = "She swallowed the cat to catch the bird.\n"
    @dog = "She swallowed the dog to catch the cat.\n"
    @goat = "She swallowed the goat to catch the dog.\n"
    @cow = "She swallowed the cow to catch the goat.\n"
  end

  def verse(n)
    case n
    when 1
      @swallowed + "fly.\n" + @why 
    when 2 
      @swallowed + "spider.\n" + "It" + @wriggled + @catchFly + @why 
    when 3
      @swallowed + "bird.\nHow absurd to swallow a bird!\n" + @bird + @catchFly + @why
    when 4
      @swallowed + "cat.\nImagine that, to swallow a cat!\n" + @cat + @bird + @catchFly + @why
    when 5
      @swallowed + "dog.\nWhat a hog, to swallow a dog!\n" + @dog + @cat + @bird + @catchFly + @why
    when 6
      @swallowed + "goat.\nJust opened her throat and swallowed a goat!\n" + @goat + @dog + @cat + @bird + @catchFly + @why
    when 7
      @swallowed + "cow.\nI don't know how she swallowed a cow!\n" + @cow + @goat + @dog + @cat + @bird + @catchFly + @why
    when 8
      @swallowed + "horse.\nShe's dead, of course!\n"
    end
  end

  def verses(s, f)
    (s..f).inject('') { |x, y| x + verse(y) + "\n"}
  end

  def sing
    verses(1, 8)
  end
end
