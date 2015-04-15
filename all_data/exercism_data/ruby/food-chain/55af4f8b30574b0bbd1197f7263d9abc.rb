class Verse
  def initialize animals
    @animals = animals
  end
  def text
    lines = []

    lines << @animals.first.intro
    lines << @animals.first.second_line

    @animals.each_cons(2){ |animal, next_animal| 
      lines << animal.catch_line(next_animal)
    }

    lines << @animals.last.second_line if @animals.count > 1
    lines.map{|l| l+"\n" }.join
  end
end

class Animal
  def initialize name, second_line, description = nil
    @name = name
    @second_line = second_line
    @description = description
  end

  attr_reader :second_line

  def intro
    "I know an old lady who swallowed a #{@name}."
  end
  def catch_line next_animal
    "She swallowed the #{@name} to catch the #{next_animal}."
  end

  def to_s
    return @name unless @description
    "#{@name} #{@description}"
  end
end

class FoodChainSong
  def verse verse_number
    verse = Verse.new get_verse_animals(verse_number)
    verse.text
  end

  def verses(start_verse, end_verse)
    (start_verse..end_verse).map{|n| "#{verse n}\n" }.join
  end

  def sing
    verses(1, @@animals.count + 1)
  end

  private 

  def get_verse_animals verse_number
    return [@@horse] if verse_number > @@animals.count

    @@animals[0...verse_number].reverse
  end

  @@animals = [
    Animal.new("fly", "I don't know why she swallowed the fly. Perhaps she'll die."),
    Animal.new("spider", "It wriggled and jiggled and tickled inside her.", "that wriggled and jiggled and tickled inside her"),
    Animal.new("bird", "How absurd to swallow a bird!"),
    Animal.new("cat", "Imagine that, to swallow a cat!"),
    Animal.new("dog", "What a hog, to swallow a dog!"),
    Animal.new("goat", "Just opened her throat and swallowed a goat!"),
    Animal.new("cow", "I don't know how she swallowed a cow!")
  ]

  @@horse = Animal.new("horse", "She's dead, of course!")

end
