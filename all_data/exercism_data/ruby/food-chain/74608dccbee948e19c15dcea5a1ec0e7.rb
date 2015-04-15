class FoodChainSong

  def verse(number)
    return "I know an old lady who swallowed a horse.\nShe's dead, of course!\n" if number == 8

    animals = %w(fly spider bird cat dog goat cow horse)
    middle_phrases = (number - 1).times.collect { |num| "She swallowed the #{animals[num + 1]} to catch the #{animals[num]}.\n" }.reverse.join

    case number
    when 2
      rhyme = "It wriggled and jiggled and tickled inside her.\n"
    when 3
      rhyme = "How absurd to swallow a bird!\n"
    when 4
      rhyme = "Imagine that, to swallow a cat!\n"
    when 5
      rhyme = "What a hog, to swallow a dog!\n"
    when 6
      rhyme = "Just opened her throat and swallowed a goat!\n"
    when 7
      rhyme = "I don't know how she swallowed a cow!\n"
    else
      rhyme = ""
    end

    middle_phrases.sub!("spider","spider that wriggled and jiggled and tickled inside her") if number != 2

    "I know an old lady who swallowed a #{animals[number - 1]}.\n#{rhyme}#{middle_phrases}I don't know why she swallowed the fly. Perhaps she'll die.\n"
  end

  def verses(start, ending)
    (start..ending).collect { |number| verse(number) + "\n" }.join
  end

  def sing
    verses(1,8)
  end

end
