class Animal < Struct.new(:prey)
  def verse
    [intro, comment, reason, outro, ''].compact.join("\n")
  end


  private

  def name
    self.class.name.downcase
  end

  def intro
    "I know an old lady who swallowed a #{name}."
  end

  def outro
    'I don\'t know why she swallowed the fly. Perhaps she\'ll die.'
  end


  protected

  def external_name
    name
  end

  def reason
    ["She swallowed the #{name} to catch the #{prey.external_name}.",
     prey.reason].compact.join "\n"
  end

  def comment
    nil
  end
end

class Fly < Animal
  def reason
    nil
  end
end

class Spider < Animal
  def comment
    'It wriggled and jiggled and tickled inside her.'
  end

  def external_name
    'spider that wriggled and jiggled and tickled inside her'
  end
end

class Bird < Animal
  def comment
    'How absurd to swallow a bird!'
  end
end

class Cat < Animal
  def comment
    'Imagine that, to swallow a cat!'
  end
end

class Dog < Animal
  def comment
    'What a hog, to swallow a dog!'
  end
end

class Goat < Animal
  def comment
    'Just opened her throat and swallowed a goat!'
  end
end

class Cow < Animal
  def comment
    'I don\'t know how she swallowed a cow!'
  end
end

class Horse < Animal
  def verse
    [intro, 'She\'s dead, of course!', ''].join("\n")
  end
end

class FoodChainSong
  ANIMAL_CLASSES = [Fly, Spider, Bird, Cat, Dog, Goat, Cow, Horse]
  ANIMALS = ANIMAL_CLASSES.reduce([]){ |array, klass| array << klass.new(array.last) }

  def verse(num)
    ANIMALS[num - 1].verse
  end

  def verses(from, to)
    (from..to).map{ |num| verse(num) }.join("\n") << "\n"
  end
  
  def sing
    verses(1, ANIMALS.length)
  end
end
