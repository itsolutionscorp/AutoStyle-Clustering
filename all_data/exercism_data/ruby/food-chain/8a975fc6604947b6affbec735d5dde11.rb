class FoodChainSong

  Refrain = {
    fly:     "I don't know why she swallowed the fly.",
    spider:  "It wriggled and jiggled and tickled inside her.",
    bird:    "How absurd to swallow a bird!",
    cat:     "Imagine that, to swallow a cat!",
    dog:     "What a hog, to swallow a dog!",
    goat:    "Just opened her throat and swallowed a goat!",
    cow:     "I don't know how she swallowed a cow!",
    horse:   "She's dead, of course!\n",
  }

  Oroder = %w(fly spider bird cat dog goat cow horse)

  def initialize 
    @song = ''
  end

  def sing
    verses 1,8
  end

  def verses start, finish
    (start..finish).each do |v|
      verse v
      @song << "\n"
    end
    @song
  end

  def final_verse
      " Perhaps she'll die.\n"
  end

  def verse n
    animals = Refrain.keys.slice(0,n).reverse
    animal = animals[0]
    @song << "I know an old lady who swallowed a #{animal}."
    @song << "\n#{Refrain[animal]}"
    return @song if animal == :horse

    if animal == :fly
      @song << final_verse
      return @song
    end

    chain animals
  end

  def chain animals
    animal = animals.shift
    @song << "\n"
    if animals.empty?
      @song << "#{Refrain[animal]}"
      @song << final_verse
      return @song
    end

    next_animal = animals[0]
    @song << "She swallowed the #{animal} to catch the #{next_animal}"
    if next_animal == :spider
      @song << " " + Refrain[next_animal].gsub('It', 'that')
    else
      @song << "."
    end
    chain animals
  end
end
