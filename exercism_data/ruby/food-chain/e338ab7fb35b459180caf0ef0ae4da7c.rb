require 'ostruct'

class FoodChainSong

  FOOD_CHAIN = [
    { type: "fly", rhyme: "I don't know why she swallowed the fly. Perhaps she'll die." },
    { type: "spider", rhyme: "It wriggled and jiggled and tickled inside her.",
                      alt_rhyme: " that wriggled and jiggled and tickled inside her" },
    { type: "bird", rhyme: "How absurd to swallow a bird!" },
    { type: "cat", rhyme: "Imagine that, to swallow a cat!" },
    { type: "dog", rhyme: "What a hog, to swallow a dog!" },
    { type: "goat", rhyme: "Just opened her throat and swallowed a goat!" },
    { type: "cow", rhyme: "I don't know how she swallowed a cow!" },
    { type: "horse", rhyme: "She's dead, of course!" }
  ]

  def verse(number)
    @song_words = FoodChainSong::Words.new(
      diner: 'old lady',
      diner_pronoun: 'she',
      animal: FoodChainSong::Animals.new(FOOD_CHAIN).animal(number)
    ).compose
  end

  def verses(first, last)
    (first..last).map { |number| verse(number) + "\n" }.join
  end

  def sing
    verses(1, FOOD_CHAIN.length)
  end

end

class FoodChainSong::Words
  VOWELS = %w(a e i o u)

  def initialize(args)
    @diner = args.fetch(:diner)
    @diner_pronoun = args.fetch(:diner_pronoun)
    @animal = args.fetch(:animal)
  end

  def compose
    words = swallowed
    return words if @animal.last?
    words << swallowed_to_catch
    words << "#{@animal.rhyme}\n"
  end

  private

  def swallowed
    words = "I know #{a_diner} who swallowed #{an_animal}.\n"
    words << "#{@animal.rhyme}\n" if @animal.prey
    words
  end

  def swallowed_to_catch(words = '')
    return words unless @animal.prey
    words << "#{@diner_pronoun.capitalize} swallowed the #{@animal} "\
             "to catch the #{@animal.prey}"\
             "#{@animal.prey.alt_rhyme}.\n"
    @animal = @animal.prey
    swallowed_to_catch(words)
  end

  def a_diner
    "#{an(@diner)} #{@diner}"
  end

  def an_animal
    "#{an(@animal)} #{@animal}"
  end

  def an(noun)
    VOWELS.include?(noun.to_s[0]) ? 'an' : 'a'
  end

end

class FoodChainSong::Animals

  def initialize(food_chain)
    @food_chain = food_chain
    @animals = build_animal_chain
  end

  def animal(number)
    @animals[number - 1]
  end

  private

  def build_animal_chain
    prey = nil
    chain = @food_chain.map do |food|
      prey = FoodChainSong::Animal.new(food.merge(prey: prey))
    end
    prey.last = true
    chain
  end

end

class FoodChainSong::Animal
  attr_accessor :type, :rhyme, :alt_rhyme, :prey, :last
  alias_method :to_s, :type
  alias_method :last?, :last

  def initialize(args)
    @type = args.fetch(:type)
    @rhyme = args.fetch(:rhyme)
    @alt_rhyme = args[:alt_rhyme]
    @prey = args.fetch(:prey)
    @last = args.fetch(:last, false)
  end

end
