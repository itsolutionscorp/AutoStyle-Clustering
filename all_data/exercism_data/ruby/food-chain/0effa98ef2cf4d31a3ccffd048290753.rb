class FoodChainSong
  def sing
    verses 1, 8
  end

  def verses fst, lst
    (fst..lst).reduce("") { |r, n| r + verse(n) + "\n" }
  end

  def verse n
    parts = VerseParts.new n
    parts.beginning + parts.desc + parts.middle + parts.the_end
  end
end

class VerseParts
  def initialize verse
    @verse = verse
    @animal = VerseAnimal.new verse
  end

  def beginning
    "I know an old lady who swallowed a #{@animal.name}.\n"
  end

  def desc
    @animal.description
  end

  def middle
    animal = @animal
    last? ? "" : @verse.downto(2).reduce("") do |result, n|
      line = eats(animal.name, animal.previous.name) + middle_ending(n)
      animal = animal.previous
      result + line
    end
  end

  def the_end
    last? ? "" : "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  private
  def last?
    @verse == 8
  end

  def eats a, b
    "She swallowed the #{a} to catch the #{b}"
  end

  def middle_ending n
    n == 3 ? " that wriggled and jiggled and tickled inside her.\n" : ".\n"
  end
end

class VerseAnimal
  attr_reader :name, :description

  def initialize verse
    animals = {
        "" => "",
        "fly" => "",
        "spider" => "It wriggled and jiggled and tickled inside her.\n",
        "bird" => "How absurd to swallow a bird!\n",
        "cat" => "Imagine that, to swallow a cat!\n",
        "dog" => "What a hog, to swallow a dog!\n",
        "goat" => "Just opened her throat and swallowed a goat!\n",
        "cow" => "I don't know how she swallowed a cow!\n",
        "horse" => "She's dead, of course!\n"
    }
    @verse, @name, @description = verse, animals.keys[verse], animals.values[verse]
  end

  def previous
    VerseAnimal.new @verse-1
  end
end
