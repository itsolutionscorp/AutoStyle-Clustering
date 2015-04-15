class FoodChainSong

  attr_accessor :animals, :starter, :swallow

  def initialize
    self.animals = ['fly', 'spider', 'bird', 'cat', 'dog', 'goat', 'cow', 'horse']
    self.starter = ['It wriggled and jiggled and tickled inside her.', 'How absurd to swallow a bird!', 'Imagine that, to swallow a cat!', 'What a hog, to swallow a dog!', 'Just opened her throat and swallowed a goat!', "I don't know how she swallowed a cow!"]
    self.swallow = ["I don't know why she swallowed the fly. Perhaps she'll die.", "She swallowed the spider to catch the fly.", "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.", "She swallowed the cat to catch the bird.", "She swallowed the dog to catch the cat.", "She swallowed the goat to catch the dog.", "She swallowed the cow to catch the goat."]
  end

  def verse(i)
    verse_array(i).join("\n") + "\n"
  end

  def verses(from, to)
    (from..to).collect { |i| verse(i) }.join("\n") + "\n"
  end

  def sing
    verses(1, 8)
  end

  private

  def verse_array(nr)
    lyrics = []
    lyrics << "I know an old lady who swallowed a #{animals[nr-1]}."

    return (lyrics + ["She's dead, of course!"]) if nr == 8

    lyrics << starter[nr-2] if nr >= 2

    ending = []
    ending = (0..[nr-1,0].max).collect {|i| swallow[i] }.reverse

    lyrics.concat ending
  end

end
