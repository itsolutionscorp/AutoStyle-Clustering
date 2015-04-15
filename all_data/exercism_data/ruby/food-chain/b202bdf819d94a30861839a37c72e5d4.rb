class FoodChainSong
  def verse(num)
    case num
      when 1 then "I know an old lady who swallowed a fly.\n"\
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      when 2 then "I know an old lady who swallowed a spider.\n"\
      "It wriggled and jiggled and tickled inside her.\n"\
      "She swallowed the spider to catch the fly.\n"\
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      when 3 then "I know an old lady who swallowed a bird.\n"\
      "How absurd to swallow a bird!\n"\
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"\
      "She swallowed the spider to catch the fly.\n"\
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      when 4 then "I know an old lady who swallowed a cat.\n"\
      "Imagine that, to swallow a cat!\n"\
      "She swallowed the cat to catch the bird.\n"\
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"\
      "She swallowed the spider to catch the fly.\n"\
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
      when 5 then "I know an old lady who swallowed a dog.\n" \
      "What a hog, to swallow a dog!\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
      when 6 then "I know an old lady who swallowed a goat.\n" \
      "Just opened her throat and swallowed a goat!\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
      when 7 then "I know an old lady who swallowed a cow.\n" \
      "I don't know how she swallowed a cow!\n" \
      "She swallowed the cow to catch the goat.\n" \
      "She swallowed the goat to catch the dog.\n" \
      "She swallowed the dog to catch the cat.\n" \
      "She swallowed the cat to catch the bird.\n" \
      "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" \
      "She swallowed the spider to catch the fly.\n" \
      "I don't know why she swallowed the fly. " \
      "Perhaps she'll die.\n"
      when 8 then "I know an old lady who swallowed a horse.\n" \
      "She's dead, of course!\n"
    end
  end

  def verses(num1, num2)
    str = ''
    (num1..num2).each do |i|
      str += self.verse(i) + "\n"
    end
    str
  end

  def sing
    self.verses(1,8)
  end

end
