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

  def intro
    "I know an old lady who swallowed a #{animal}.\n#{remark}\n"
  end

  def reason(previous_verse)
    "She swallowed the #{animal} to catch the #{previous_verse.animal_with_remark}\n"
  end

  def reasons(previous_verses)
    reasons = []
    previous_verses.push(self).each_cons(2) do |previous_verse, verse|
      reasons << verse.reason(previous_verse)
    end
    reasons.reverse.join
  end

  def chorus(previous_verses)
    reasons(previous_verses) + previous_verses.first.remark + "\n"
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
    verse = @verses[number - 1]
    case number
    when 1, @verses.length
      verse.intro
    else
      verse.intro + verse.chorus(previous_verses(number))
    end
  end

  def verses(from, to)
    (from..to).each_with_object("") do |number, lyrics|
      lyrics << verse(number) + "\n"
    end
  end

  def sing
    verses(1, @verses.length)
  end

  private

  def previous_verses(number)
    @verses[0...number - 1]
  end
end
