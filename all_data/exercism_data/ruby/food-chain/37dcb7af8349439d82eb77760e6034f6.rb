class FoodChainSong
  attr_reader :song 
  def song
    @song = ::FoodChainSong.new
  end

  def verse(i)
    one = "I know an old lady who swallowed a fly.\nI don't know why she swallowed the fly. Perhaps she'll die.\n"
    
    if 1 == i
      return one
    end


    two = "I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      
      if i == 2
        return two
      end

    three = "I know an old lady who swallowed a bird.\n" \
      "How absurd to swallow a bird!\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      
      if i == 3
        return three
      end

      four = "I know an old lady who swallowed a cat.\n" \
      "Imagine that, to swallow a cat!\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"

      if i == 4
        return four
      end

      five = "I know an old lady who swallowed a dog.\n" \
      "What a hog, to swallow a dog!\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"

      if i == 5 
        return five
      end

      six = "I know an old lady who swallowed a goat.\n" \
      "Just opened her throat and swallowed a goat!\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"

      if i == 6
        return six
      end

      seven = "I know an old lady who swallowed a cow.\n" \
      "I don't know how she swallowed a cow!\n" \
      "She swallowed the cow to catch the goat.\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"

      if i == 7
        return seven
      end

      eight = "I know an old lady who swallowed a horse.\n" \
      "She's dead, of course!\n"

      if i == 8
        return eight
      end
  end

  def verses(first, last)
    first.upto(last).map do |number|
      verse(number)
    end.join("\n") + "\n"
  end
  
  def sing
    verses(1,8)
  end
end
