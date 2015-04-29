class FoodChainVerse
  attr_reader :animal, :remark

  def initialize(animal:, remark:, animal_with_remark: nil)
    @animal = animal
    @remark = remark
    @animal_with_remark = animal_with_remark
  end

  def animal_with_remark
    @animal_with_remark || @animal + "."
  end
end

class FoodChainSong
  def initialize
    @verses = [
      FoodChainVerse.new(animal: "fly", remark: "I don't know why she swallowed the fly. Perhaps she'll die."),
      FoodChainVerse.new(animal: "spider", remark: "It wriggled and jiggled and tickled inside her.",
                         animal_with_remark: "spider that wriggled and jiggled and tickled inside her."),
      FoodChainVerse.new(animal: "bird", remark: "How absurd to swallow a bird!"),
      FoodChainVerse.new(animal: "cat", remark: "Imagine that, to swallow a cat!"),
      FoodChainVerse.new(animal: "dog", remark: "What a hog, to swallow a dog!"),
      FoodChainVerse.new(animal: "goat", remark: "Just opened her throat and swallowed a goat!"),
      FoodChainVerse.new(animal: "cow", remark: "I don't know how she swallowed a cow!"),
      FoodChainVerse.new(animal: "horse", remark: "She's dead, of course!")
    ]
  end

  def verse(number)
    lyrics(@verses[number - 1])
  end

  def verses(from, to)
    (from..to).each_with_object("") do |number, verses|
      verses << verse(number) + "\n"
    end
  end

  def sing
    verses(1, @verses.length)
  end

  private

  def first_or_last?(verse)
    verse == @verses.first || verse == @verses.last
  end

  def verses_upto(verse)
    @verses[0..@verses.index(verse)]
  end

  def intro(verse)
    "I know an old lady who swallowed a #{verse.animal}.\n#{verse.remark}\n"
  end

  def reason(verse, previous_verse)
    "She swallowed the #{verse.animal} to catch the #{previous_verse.animal_with_remark}\n"
  end

  def reasons(verse)
    reasons = ""
    verses_upto(verse).each_cons(2) do |previous_verse, verse|
      reasons.prepend(reason(verse, previous_verse))
    end
    reasons
  end

  def chorus(verse)
    reasons(verse) + @verses.first.remark + "\n"
  end

  def lyrics(verse)
    return intro(verse) if first_or_last?(verse)
    intro(verse) + chorus(verse)
  end
end
