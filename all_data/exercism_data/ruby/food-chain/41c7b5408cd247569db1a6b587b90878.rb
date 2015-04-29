class FoodChainSong

  AnimalNames = [ :fly, :spider, :bird, :cat, :dog, :goat, :cow, :horse ]

  def verse number
    Verse.new( animals_for_verse number ).lyrics
  end

  def verses first, last
    verses = (first..last).map { |number| verse number }

    [ *verses, '' ].join "\n"
  end

  def sing
    verses( 1, AnimalNames.size )
  end

private

  def animals_for_verse number
    AnimalNames[0...number].map do |animal_name|
      animal_class_for( animal_name ).new
    end
  end

  def animal_class_for name
    class_name = name.to_s.capitalize

    self.class.const_get class_name
  end

end


class FoodChainSong::Verse

  def initialize animals
    reversed_animals = animals.reverse

    @current_animal = reversed_animals.first
    @animals        = reversed_animals
  end

  def lyrics
    if current_animal.kills_her?
      [ opening, '' ].join "\n"
    else
      [ opening, *body, ending, '' ].compact.join "\n"
    end
  end

private

  attr_reader :current_animal, :animals

  def opening
    "I know an old lady who swallowed a #{ current_animal }.\n#{ current_animal.action }"
  end

  def body
    animals.each_cons(2).map do |(one, other)|
      "She swallowed the #{ one } to catch the #{ other.body_name }."
    end
  end

  def ending
    animals.last.action unless animals.size == 1
  end

end


class FoodChainSong::Animal

  def to_s
    self.class.name.gsub('FoodChainSong::','').downcase
  end

  alias_method :body_name, :to_s

  def action
    raise NotImplementedError, "All animals need an action"
  end

  def kills_her?
    false
  end

end


class FoodChainSong

  class Fly < Animal
    def action
      "I don't know why she swallowed the fly. Perhaps she'll die."
    end
  end

  class Spider < Animal
    def body_name
      "#{ to_s } that #{ action.gsub('It ', '').gsub('.', '') }"
    end

    def action
      "It wriggled and jiggled and tickled inside her."
    end
  end

  class Bird < Animal
    def action
      "How absurd to swallow a bird!"
    end
  end

  class Cat < Animal
    def action
      "Imagine that, to swallow a cat!"
    end
  end

  class Dog < Animal
    def action
      "What a hog, to swallow a dog!"
    end
  end

  class Goat < Animal
    def action
      "Just opened her throat and swallowed a goat!"
    end
  end

  class Cow < Animal
    def action
      "I don't know how she swallowed a cow!"
    end
  end

  class Cow < Animal
    def action
      "I don't know how she swallowed a cow!"
    end
  end

  class Horse < Animal
    def action
      "She's dead, of course!"
    end

    def kills_her?
      true
    end
  end

end
