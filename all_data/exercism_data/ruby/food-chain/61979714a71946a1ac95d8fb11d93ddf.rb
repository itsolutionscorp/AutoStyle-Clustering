module Animals
  def animals
    %w(fly spider bird cat dog goat cow horse)
  end

  def bird
    "How absurd to swallow a bird!\n"
  end

  def cat
    "Imagine that, to swallow a cat!\n"
  end

  def spider
    "It wriggled and jiggled and tickled inside her.\n"
  end

  def dog
    "What a hog, to swallow a dog!\n"
  end

  def goat
    "Just opened her throat and swallowed a goat!\n"
  end

  def cow
    "I don't know how she swallowed a cow!\n"
  end

  def horse
    "I know an old lady who swallowed a horse.\n" +
      "She's dead, of course!\n"
  end
end

class FoodChainSong
  include Animals

  def sing
    verses(1, 8)
  end

  def verses(start, finish)
    song = ""
    (0..2).to_a.inject do |r, n|
      song << verse(n) + "\n"
    end
  end

  def verse(num)
    return horse if num == 8
    return header(animals[0]) + footer if num == 1
    string = header(animals[num - 1]) 
    string << self.send(animals[num - 1]) + lines(num) unless num == 1
    string << footer
  end

  def lines(number)
    string = ""
    (1..number).to_a.reverse.inject do |r, n|
      string << swallowed(n)
    end
  end

  def swallowed(victim)
    string = "She swallowed the #{animals[victim]} to catch the #{animals[victim-1]}"

    if victim == 2
      string << " that " + spider.slice(3..-1)
    else 
      string << ".\n"
    end
    
    string
  end

  def header(animal)
    "I know an old lady who swallowed a #{animal}.\n"
  end

  def footer
    "I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end
end
