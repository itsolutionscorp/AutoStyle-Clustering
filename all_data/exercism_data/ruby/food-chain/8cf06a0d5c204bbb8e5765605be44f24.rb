require 'pry'

class FoodChainSong
  def initialize
  end

  ANIMALS = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]

  def sing
    self.verses(1, 8)
  end

  def verses(verse_1, verse_2)
    "#{verse(verse_1)}\n#{verse(verse_2)}\n"
  end

  def verse(n)
    n -= 1
    animals = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]
    verse_string = ""
    verse_string += what_did_she_swallow(animals[n])
    if n > 0  then verse_string += introduce_animal(animals[n]) end
    if n == 7 then return verse_string end
    if n > 0 then verse_string += why_swallow_animals(n) end
    verse_string += conclude_song(n)
    verse_string
  end

  def introduce_animal(animal)
    animal = animal.to_sym
    animal_introductions = { :spider => "It wriggled and jiggled and tickled inside her.\n",
                             :bird   => "How absurd to swallow a bird!\n",
                             :cat    => "Imagine that, to swallow a cat!\n",
                             :dog    => "What a hog, to swallow a dog!\n",
                             :goat   => "Just opened her throat and swallowed a goat!\n",
                             :cow    => "I don't know how she swallowed a cow!\n",
                             :horse  => "She's dead, of course!\n" }
    animal_introductions[animal]
  end

  def what_did_she_swallow(animal)
    "I know an old lady who swallowed a #{animal}.\n"
  end

  def conclude_song(n)
    if n == 8
      "She's dead, of course!"
    else
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
  end

  def why_swallow_this_animal(animal_1, animal_2)
    spider = animal_2 == "spider" ? " that wriggled and jiggled and tickled inside her" : ""
    "She swallowed the #{animal_1} to catch the #{animal_2}#{spider}.\n"
  end

  def why_swallow_animals(n)
    result = ""
    animals = ["fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse"]
    backwards_range(1..n).each do |i|
      result += why_swallow_this_animal(animals[i], animals[i-1])
    end
    result
  end

  def backwards_range(range)
    # todo: This is really silly. I know there has to be a better implementation for a range starting at the end and counting backwards
    # But I don't know what is yet.
    # So I'm writing this function as a kind of placeholder until I can find that better implementation, which I'm just trusting must exist
    result_array = []
    range.each do |i|
      result_array.unshift(i)
    end
    result_array
  end

end
