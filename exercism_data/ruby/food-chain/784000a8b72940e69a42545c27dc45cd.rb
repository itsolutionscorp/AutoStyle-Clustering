require 'ostruct'

class FoodChainSong

  def verse(number)
    @animal = FoodChainAnimals.animal(number)
    @song_words = FoodChainSongWords.new(diner: "old lady", diner_pronoun: "she", animal: @animal)
    @song_words.build
  end

  def verses(first, last)
    (first..last).map{|number| verse(number) + "\n" }.join
  end

  def sing
    verses(1, FoodChainAnimals::FOOD_CHAIN.length)
  end

end

class FoodChainAnimals

  FOOD_CHAIN = [
    {type: "fly", rhyme: "I don't know why she swallowed the fly. Perhaps she'll die."},
    {type: "spider", rhyme: "It wriggled and jiggled and tickled inside her.", alt_rhyme: " that wriggled and jiggled and tickled inside her"},
    {type: "bird", rhyme: "How absurd to swallow a bird!"},
    {type: "cat", rhyme: "Imagine that, to swallow a cat!"},
    {type: "dog", rhyme: "What a hog, to swallow a dog!"},
    {type: "goat", rhyme: "Just opened her throat and swallowed a goat!"},
    {type: "cow", rhyme: "I don't know how she swallowed a cow!"},
    {type: "horse", rhyme: "She's dead, of course!"}
  ]

  def initialize
    @animals = build_animals
  end

  def self.animal(number)
    new.animal(number)
  end

  def animal(number)
    @animals[number - 1]
  end

  private

  def build_animals
    prey = nil
    food_chain = FOOD_CHAIN.map do |food_chain|
      prey = OpenStruct.new( food_chain.merge(prey: prey) )
    end
    prey.send("last?=", true)
    food_chain
  end

end

class FoodChainSongWords
  VOWELS = %w{a e i o u}

  def initialize(args)
    @diner = args.fetch(:diner)
    @diner_pronoun = args.fetch(:diner_pronoun)
    @animal = args.fetch(:animal)
  end

  def build
    words = swallowed
    return words if @animal.last?
    words << swallowed_to_catch
    words << verse_end
  end

  private

  def swallowed
    words = "I know #{a_diner} who swallowed #{an_animal}.\n"
    words << "#{@animal.rhyme}\n" if @animal.prey
    words
  end

  def swallowed_to_catch(words = "")
    return words unless @animal.prey
    words << "#{@diner_pronoun.capitalize} swallowed the #{@animal.type} to catch the #{@animal.prey.type}#{@animal.prey.alt_rhyme}.\n"
    @animal = @animal.prey
    swallowed_to_catch(words)
  end

  def verse_end
    "#{@animal.rhyme}\n"
  end

  def a_diner
     "#{an(@diner)} #{@diner}"
  end

  def an_animal
    "#{an(@animal.type)} #{@animal.type}"
  end

  def an(noun)
    VOWELS.include?(noun[0]) ? "an" : "a"
  end

end
