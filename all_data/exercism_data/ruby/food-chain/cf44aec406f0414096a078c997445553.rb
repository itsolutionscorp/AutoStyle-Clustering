class FoodChainSong
  def verse(n)
    Creature[n - 1].verse
  end

  def verses(from, to)
    (from..to).map { |i| verse(i) }.join("\n") << "\n"
  end

  def sing
    verses 1, 8
  end
end

class Creature
  attr_reader :verse

  def initialize(prev, name, unique_line, do_chorus = true, name2 = name)
    @chorus =
      if prev
        "She swallowed the #{name} to catch the #{prev.name}.\n" << prev.chorus
      else
        '' << unique_line << "\n"
      end

    first_line = "I know an old lady who swallowed a #{name}.\n"

    @verse = first_line << unique_line << "\n" << (do_chorus ? chorus : '')
    @name = name2
  end

  protected

  attr_reader :chorus, :name

  #### end of instance methods

  CREATURES = []
  [
    ['fly', "I don't know why she swallowed the fly. Perhaps she'll die.", false],
    ['spider', 'It wriggled and jiggled and tickled inside her.', true, 'spider that wriggled and jiggled and tickled inside her'],
    ['bird', 'How absurd to swallow a bird!'],
    ['cat', 'Imagine that, to swallow a cat!'],
    ['dog', 'What a hog, to swallow a dog!'],
    ['goat', 'Just opened her throat and swallowed a goat!'],
    ['cow', "I don't know how she swallowed a cow!"],
    ['horse', "She's dead, of course!", false]
  ].each_with_index { |a, i| CREATURES[i] = Creature.new(CREATURES[i - 1], *a) }
  private_constant :CREATURES

  def self.[](i)
    CREATURES[i]
  end
end
