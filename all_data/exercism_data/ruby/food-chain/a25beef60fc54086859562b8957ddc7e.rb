class FoodChainSong
  def verse(num)
    animal =  case num
              when 1 then Fly.new
              when 2 then Spider.new
              when 3 then Bird.new
              when 4 then Cat.new
              when 5 then Dog.new
              when 6 then Goat.new
              when 7 then Cow.new
              when 8 then Horse.new
              end

    animal.verse
  end

  def verses(starting, ending)
    song = ''

    (starting..ending).each do |num|
      song << "#{verse(num)}\n"
    end

    song
  end

  def sing
    verses(1, 8)
  end
end

class Animal
  def self.verse
    new.verse
  end

  def self.why
    new.why
  end

  def type
    self.class.to_s.downcase
  end

  def verse
    intro << how << why << ending
  end

  def intro
    "I know an old lady who swallowed a #{type}.\n"
  end

  def how
    ''
  end

  def why
    ''
  end

  def ending
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end
end

class Fly < Animal
end

class Spider < Animal
  def how
    "It wriggled and jiggled and tickled inside her.\n"
  end

  def why
    "She swallowed the spider to catch the fly.\n"
  end
end

class Bird < Animal
  def how
    "How absurd to swallow a bird!\n"
  end

  def why
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n#{Spider.why}"
  end
end

class Cat < Animal
  def how
    "Imagine that, to swallow a cat!\n"
  end

  def why
    "She swallowed the cat to catch the bird.\n#{Bird.why}"
  end
end

class Dog < Animal
  def how
    "What a hog, to swallow a dog!\n"
  end

  def why
    "She swallowed the dog to catch the cat.\n#{Cat.why}"
  end
end

class Goat < Animal
  def how
    "Just opened her throat and swallowed a goat!\n"
  end

  def why
    "She swallowed the goat to catch the dog.\n#{Dog.why}"
  end
end

class Cow < Animal
  def how
    "I don't know how she swallowed a cow!\n"
  end

  def why
    "She swallowed the cow to catch the goat.\n#{Goat.why}"
  end
end

class Horse < Animal
  def ending
    "She's dead, of course!\n"
  end
end
