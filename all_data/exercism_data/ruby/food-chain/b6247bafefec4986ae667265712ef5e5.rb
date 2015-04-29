class Phrase

  def initialize name, rhyme, previous
    @name = name
    @rhyme = rhyme
    @previous = previous
  end

  def verse
    "I know an old lady who swallowed a #{ @name }.\n" + "#{ @rhyme }" + phrase
  end

  def phrase
    case @name
    when :fly
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    when :horse
      ""
    else
      "She swallowed the #{ @name } to catch the #{ @previous.target_name }.\n" + @previous.phrase
    end
  end

  def target_name
    @name == :spider ? spider_name : @name
  end

  private

  def spider_name
    "spider that wriggled and jiggled and tickled inside her"
  end

end

class FoodChainSong
  RHYMES = { fly:  "",
             spider:  "It wriggled and jiggled and tickled inside her.\n",
             bird:  "How absurd to swallow a bird!\n",
             cat:  "Imagine that, to swallow a cat!\n",
             dog:  "What a hog, to swallow a dog!\n",
             goat:  "Just opened her throat and swallowed a goat!\n",
             cow:  "I don't know how she swallowed a cow!\n",
             horse:  "She's dead, of course!\n" }

  PHRASE_LIST = RHYMES.map do |name, rhyme|
    @last = Phrase.new name, rhyme, @last
  end

  def verse number
    PHRASE_LIST[number-1].verse
  end

  def verses start, finish
    (start .. finish).map { |i| verse(i) + "\n" }.join
  end

  def sing
    verses 1, 8
  end

end
