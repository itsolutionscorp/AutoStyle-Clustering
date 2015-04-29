require 'pry'

class FoodChainSong
  ANIMALS = ["?", "fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]

  def sing
    self.verses(1, 8)
  end

  def verses(verse_1, verse_2)
    "#{verse(verse_1)}\n#{verse(verse_2)}\n"
  end

  def verse(n)
    verse_string = introduce_animal(ANIMALS[n])
    if n == 8 then return verse_string end
    verse_string += why_swallow_animals(n)
  end

  def introduce_animal(animal)
    result = "I know an old lady who swallowed a #{animal}.\n"
    animal_introductions = { :fly    => "",
                             :spider => "It wriggled and jiggled and tickled inside her.\n",
                             :bird   => "How absurd to swallow a bird!\n",
                             :cat    => "Imagine that, to swallow a cat!\n",
                             :dog    => "What a hog, to swallow a dog!\n",
                             :goat   => "Just opened her throat and swallowed a goat!\n",
                             :cow    => "I don't know how she swallowed a cow!\n",
                             :horse  => "She's dead, of course!\n" }
    result += animal_introductions[animal.to_sym]
  end

  def why_swallow_this_animal(animal_1, animal_2)
    spider = animal_2 == "spider" ? " that wriggled and jiggled and tickled inside her" : ""
    fly = animal_1 == "fly" ? "I don't know why she swallowed the fly. Perhaps she'll die.\n" : nil
    if fly
      return fly
    else
      return "She swallowed the #{animal_1} to catch the #{animal_2}#{spider}.\n"
    end
  end

  def why_swallow_animals(n)
    result = ""
    (1..n).to_a.reverse.each do |i|
      result += why_swallow_this_animal(ANIMALS[i], ANIMALS[i-1])
    end
    result
  end

end
