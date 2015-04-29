# Personally, I think if this test is meant to play with recursion, the song
# is not consistent enough... "swallowed a cow!" &" that wriggled and jiggled"

class FoodChainSong

  def sing
    verses(1, 8)
  end

  def verses(x, y)
    verse(x, y)
  end

  def verse(x, y=x, song="")

    ANIMALS[(x-1)..(y-1)].each do |animal| 
      
      index = ANIMALS.index(animal)
      song << "I know an old lady who swallowed a #{animal.to_s}.#{SEPERATOR}"
      song << DESCRIPTIONS[index]
      song << SEPERATOR if animal != :fly
      break if animal == :horse

      index.downto(1) do |i|
        song << "She swallowed the #{ANIMALS[i]} to catch the #{ANIMALS[i-1]}.#{SEPERATOR}"
        case ANIMALS[i-1]
        when :spider then song[-2..-1] = " that wriggled and jiggled and tickled inside her.#{SEPERATOR}"
        when :fly then song << DESCRIPTIONS[i-1]
        end
      end

      song << " Perhaps she'll die.#{SEPERATOR}"
      song << SEPERATOR if x != y
    end
    song
  end

  SEPERATOR = "\n"

  ANIMALS_DESCRIPTIONS = {
    fly: "I don't know why she swallowed the fly.", 
    spider: "It wriggled and jiggled and tickled inside her.", 
    bird: "How absurd to swallow a bird!",
    cat: "Imagine that, to swallow a cat!",
    dog: "What a hog, to swallow a dog!",
    goat: "Just opened her throat and swallowed a goat!",
    cow: "I don't know how she swallowed a cow!",
    horse: "She's dead, of course!"
  }

  ANIMALS       = ANIMALS_DESCRIPTIONS.keys
  DESCRIPTIONS  = ANIMALS_DESCRIPTIONS.values

end
