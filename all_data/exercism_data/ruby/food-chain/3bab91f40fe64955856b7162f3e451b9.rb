class FoodChainSong

  REACTIONS = {
    fly: '',
    spider: "It wriggled and jiggled and tickled inside her.\n",
    bird: "How absurd to swallow a bird!\n",
    cat: "Imagine that, to swallow a cat!\n",
    dog: "What a hog, to swallow a dog!\n",
    goat: "Just opened her throat and swallowed a goat!\n",
    cow: "I don't know how she swallowed a cow!\n",
    horse: "She's dead, of course!\n"
  }

  ANIMALS = REACTIONS.keys

  def verse number
    animal = ANIMALS[number -= 1]
    String.new(old_lady_swallowed_an animal).tap do |s|
      s << reaction_to_swallowing_an(animal)
      next if animal.eql? :horse
      s << recap_countdown(number)
      s << perhaps_shell_die
    end
  end

  def verses *numbers
    String.new("").tap do |verses_text|
      numbers.each { |n| verses_text << verse(n) << "\n" }
    end
  end

  def sing
    verses 1, ANIMALS.count
  end

  private

    def old_lady_swallowed_an animal_swallowed
      "I know an old lady who swallowed a #{animal_swallowed}.\n"
    end

    def reaction_to_swallowing_an animal_swallowed
      REACTIONS[animal_swallowed]
    end

    def recap_countdown from_number
      String.new("").tap do |s|
        from_number.downto(0).each do |animal_idx|
          s << recap_why(ANIMALS[animal_idx], ANIMALS[animal_idx - 1])
        end
      end
    end

    def recap_why predator, prey
      return "" if predator.eql? :fly
      String.new("She swallowed the #{predator} to catch the ").tap do |recap|
        if prey.eql? :spider
          recap << "spider that wriggled and jiggled and tickled inside her.\n"
        else
          recap << "#{prey}.\n"
        end
      end
    end

    def perhaps_shell_die
      "I don't know why she swallowed the fly. Perhaps she'll die.\n"
    end
end
