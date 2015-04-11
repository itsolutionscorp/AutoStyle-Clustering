class FoodChainSong

  FIRST_LINE = "I know an old lady who swallowed a %s.\n%s\n"
  REPEATING_LINE = "She swallowed the %s to catch the %s.\n"

  class Food
    def initialize(animal_name, phrase, animal_description = nil)
      @animal_name = animal_name
      @phrase = phrase
      @animal_description = animal_description ? animal_description : animal_name
      @suffix = @phrase + "\n"
      @sing = FIRST_LINE % [@animal_name, @phrase]
    end
    attr_reader :animal_description, :suffix, :sing
  end

  class RecursiveFood < Food
    def initialize(animal_name, phrase, next_animal, animal_description = nil)
      super(animal_name, phrase, animal_description)
      @suffix = REPEATING_LINE % [@animal_name, next_animal.animal_description] + next_animal.suffix
      @sing += suffix
    end
  end

  VERSES = []
  VERSES.push(Food.new("fly", "I don't know why she swallowed the fly. Perhaps she'll die."))
  VERSES.push(RecursiveFood.new("spider", "It wriggled and jiggled and tickled inside her.", VERSES[-1], "spider that wriggled and jiggled and tickled inside her"))
  VERSES.push(RecursiveFood.new("bird", "How absurd to swallow a bird!", VERSES[-1]))
  VERSES.push(RecursiveFood.new("cat", "Imagine that, to swallow a cat!", VERSES[-1]))
  VERSES.push(RecursiveFood.new("dog", "What a hog, to swallow a dog!", VERSES[-1]))
  VERSES.push(RecursiveFood.new("goat", "Just opened her throat and swallowed a goat!", VERSES[-1]))
  VERSES.push(RecursiveFood.new("cow", "I don't know how she swallowed a cow!", VERSES[-1]))
  VERSES.push(Food.new("horse", "She's dead, of course!"))

  def verse(number)
    VERSES[number - 1].sing
  end

  def verses(from, to)
    VERSES[from-1..to-1].map { |current| current.sing + "\n" }.join("")
  end

  def sing
    verses(1, VERSES.length)
  end
end
