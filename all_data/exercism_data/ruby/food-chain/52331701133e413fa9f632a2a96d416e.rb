class FoodChainSong
  def verses(start, stop)
    (start..stop).map { |x| verse(x) }.join("\n") + "\n"
  end

  def sing
    verses(1,8)
  end

  def verse(num)
    animal = ANIMALS[num-1]
    song = "I know an old lady who swallowed a #{animal}.\n"
    return song + DEATH[animal] if DEATH.key? animal

    song += "It #{EXCEPTIONS[animal]}.\n" if EXCEPTIONS.key? animal
    song += RHYMES[animal] if RHYMES.key? animal
    while num > 0
      song += "She swallowed the #{ANIMALS[num-1]} to catch the #{ANIMALS[num-2]}" if num > 1
      song += " that #{EXCEPTIONS[ANIMALS[num-2]]}" if EXCEPTIONS.key? ANIMALS[num-2]
      song += ".\n" if num > 1
      num -= 1
    end
    song + ENDING
  end

  RHYMES = {
    'bird' => "How absurd to swallow a bird!\n",
    'cat' => "Imagine that, to swallow a cat!\n",
    'dog' => "What a hog, to swallow a dog!\n",
    'goat' => "Just opened her throat and swallowed a goat!\n",
    'cow' => "I don't know how she swallowed a cow!\n"
  }
  DEATH = { 'horse' => "She's dead, of course!\n"}
  EXCEPTIONS = { 'spider' => 'wriggled and jiggled and tickled inside her' }
  ANIMALS = %w(fly spider bird cat dog goat cow horse)
  ENDING = "I don't know why she swallowed the fly. Perhaps she'll die.\n"


end
