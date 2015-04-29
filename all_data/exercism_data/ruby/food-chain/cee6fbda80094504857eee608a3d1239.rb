class FoodChainSong
  def initialize
    @animals = generate_animals
    @verses = [nil] + @animals.map{|a| Verse.new(a).sing}
  end

  def verses(start, stop)
    @verses[start..stop].join("\n") << "\n"
  end

  def sing
    verses(1,8)
  end

  def verse(i)
    @verses[i]
  end

  def generate_animals
    animals =  [["fly"],
                ["spider", "It wriggled and jiggled and tickled inside her.",  " that wriggled and jiggled and tickled inside her"],
                ["bird", "How absurd to swallow a bird!"],
                ["cat", "Imagine that, to swallow a cat!"],
                ["dog", "What a hog, to swallow a dog!"],
                ["goat", "Just opened her throat and swallowed a goat!"],
                ["cow", "I don't know how she swallowed a cow!"],
                ["horse",  nil , nil, true]]
    animals.map!{|animal| Animal.new(*animal)}
    (1...animals.length).each { |i| animals[i].catches = animals[i - 1] }
    animals
  end
end

class Verse
  attr_reader :sing

  def initialize(animal)
    @animal = animal
    @lines = []
    @sing = build_verse
  end

  def build_verse
    add_first_line
    add_animal_phrase
    add_chorus
    add_last_line
    combine_lines
  end

  def add_first_line
    @lines << "I know an old lady who swallowed a #{@animal}."
  end

  def add_animal_phrase 
    @lines << @animal.phrase if @animal.phrase
  end

  def add_chorus
    @animal.chorus_lines.each do |line| 
      @lines << line
    end
  end

  def add_last_line
    if @animal.deadly
      @lines << "She's dead, of course!"
    else
      @lines << "I don't know why she swallowed the fly. Perhaps she'll die."
    end
  end

  def combine_lines
    @lines.join("\n") << "\n"
  end

  def to_s
    @sing
  end

end

class Animal
  attr_reader :name, :phrase, :deadly, :append, :catches
  attr_writer :catches

  def initialize(name, phrase = nil, append = nil, deadly = false)
    @name = name
    @phrase = phrase
    @append = append
    @deadly  = deadly 
    @catches = nil
  end

  def chorus_lines
    return [] if deadly
    lines = []
    catchy = self
    while (catchy.catches != nil)
      lines << catchy.catch_line
      catchy = catchy.catches
    end
    lines
  end

  def catch_line
    "She swallowed the #{self} to catch the #{catches}#{catches.append}."
  end

  def to_s
    @name
  end
end
