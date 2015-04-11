class FoodChainSong
  ANIMALS = %w(fly spider bird cat dog goat cow horse)
  def verse num
    song = ""
    animals = ANIMALS[0, num].reverse
    animals.each_with_index do |animal, index|
      if index == 0
        song << "I know an old lady who swallowed a #{animal}.\n"
        song << animal_line(animal)
        return song if animal == "horse"
      else 
        song << "She swallowed the #{animals[index-1]} to catch the #{animal}"
        song << (animal == "spider" ?  " that wriggled and jiggled and tickled inside her.\n" :
                                       ".\n")
      end
      if index == num - 1
        song << "I don't know why she swallowed the fly. Perhaps she'll die.\n" 
      end
    end
    song  
  end

  def verses start, finish
    hi = (start..finish).inject("") { |song, num| song += verse(num) + "\n" }
  end
      
  def sing
    verses 1, 8
  end

  private

  def animal_line animal
    case animal
    when "spider"
      "It wriggled and jiggled and tickled inside her.\n"
    when "bird"
      "How absurd to swallow a bird!\n"
    when "cat"
      "Imagine that, to swallow a cat!\n"
    when "dog"
      "What a hog, to swallow a dog!\n"
    when "goat"
      "Just opened her throat and swallowed a goat!\n"
    when "cow"
      "I don't know how she swallowed a cow!\n"
    when "horse"
      "She's dead, of course!\n"
    else
      ""
    end
  end
end
