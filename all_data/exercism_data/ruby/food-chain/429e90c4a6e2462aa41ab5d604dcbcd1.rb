class FoodChainSong
  def verse(verse_number)
    join_lines ANIMALS[verse_number - 1].lines
  end

  def verses(first, last)
    join_lines first.upto(last).each_with_object([]) { |number, verses| verses << verse(number) }
  end

  def sing
    verses 1, 8
  end

  def join_lines(*lines)
    "#{lines.flatten.compact.join("\n")}\n"
  end

  class Animal
    attr_accessor :previous

    def initialize(name, wow = nil)
      @name, @wow = name, wow
    end

    def lines
      [first_phrase, wow_phrase, catch_phrases, ending_phrase]
    end

    def first_phrase
      "I know an old lady who swallowed a #{@name}."
    end

    def wow_phrase
      @wow
    end

    def catch_phrases
      ["She swallowed the #{@name} to catch the #{@previous.description}."] << @previous.catch_phrases if @previous
    end

    def description
      @name
    end

    def ending_phrase
      "I don't know why she swallowed the fly. Perhaps she'll die."
    end
  end

  class Spider < Animal
    def description
      "#{super} that#{@wow.sub(/It/, '').chomp(".")}"
    end
  end

  class Horse < Animal
    def ending_phrase
      "She's dead, of course!"
    end
  end

  FLY    = Animal.new :fly
  SPIDER = Spider.new :spider, "It wriggled and jiggled and tickled inside her."
  BIRD   = Animal.new :bird,   "How absurd to swallow a bird!"
  CAT    = Animal.new :cat,    "Imagine that, to swallow a cat!"
  DOG    = Animal.new :dog,    "What a hog, to swallow a dog!"
  GOAT   = Animal.new :goat,   "Just opened her throat and swallowed a goat!"
  COW    = Animal.new :cow,    "I don't know how she swallowed a cow!"
  HORSE  = Horse.new  :horse

  ANIMALS = [FLY, SPIDER, BIRD, CAT, DOG, GOAT, COW, HORSE]

  ANIMALS[0..-2].each_cons(2) { |previous, animal| animal.previous = previous }

end
