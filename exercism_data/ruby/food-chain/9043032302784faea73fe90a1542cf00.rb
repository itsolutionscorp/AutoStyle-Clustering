class Verse
  attr_reader :animal, :phrase

  def initialize(animal, phrase, *args)
    @animal, @phrase = animal, phrase
    post_initialize(*args)
  end

  def post_initialize(*args)
    raise NotImplementedError unless args.empty?
  end
  
  def start_verse
    "I know an old lady who swallowed a #{animal}.\n#{phrase}\n"
  end

  def finish_verse
    ""
  end

  def make_verse
    text = ''
    text << start_verse
    text << finish_verse
    return text
  end
end

class ChainVerse < Verse
  attr_reader :animal_sought, :extra_phrase

  def post_initialize(animal_sought, extra_phrase="")
    @animal_sought = animal_sought
    @extra_phrase = extra_phrase
  end

  def make_chain_line
    %Q(She swallowed the #{animal} \
to catch the #{animal_sought.animal}#{extra_phrase}.)
  end

  def make_chain_lines
    "#{make_chain_line}\n#{animal_sought.finish_verse}"
  end

  def finish_verse
    make_chain_lines
  end
end

class FoodChainSong
  attr_reader :song

  def initialize()
    @song = []
    @song << Verse.new("fly",
      "I don't know why she swallowed the fly. Perhaps she'll die.")
    @song << ChainVerse.new("spider",
      "It wriggled and jiggled and tickled inside her.",
      @song.last, ".\n#{@song.last.phrase[0..-2]}")
    @song << ChainVerse.new("bird",
      "How absurd to swallow a bird!",
      @song.last, " that#{@song.last.phrase[2..-2]}")
    @song << ChainVerse.new("cat",
      "Imagine that, to swallow a cat!", @song.last)
    @song << ChainVerse.new("dog",
      "What a hog, to swallow a dog!", @song.last)
    @song << ChainVerse.new("goat",
      "Just opened her throat and swallowed a goat!", @song.last)
    @song << ChainVerse.new("cow",
      "I don't know how she swallowed a cow!", @song.last)
    @song << Verse.new("horse", "She's dead, of course!")
  end

  def verse(number)
    raise ArgumentError if !number.between?(1,song.count)
    song.fetch(number.pred).make_verse
  end

  def verses(start, stop)
    start.upto(stop).each_with_object('') { |v,o| o << "#{verse(v)}\n" }
  end

  def sing
    verses(1, song.count)
  end
end
