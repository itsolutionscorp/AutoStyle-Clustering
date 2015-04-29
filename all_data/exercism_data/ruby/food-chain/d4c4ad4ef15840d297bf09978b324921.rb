class FoodChainSong
  
  def verse(index)
    raise ArgumentError unless (1..CRITTERS.size).include?(index)
    critter = CRITTERS[index - 1]

    [
      "I know an old lady who swallowed a #{critter}.",
      critter.down_the_hatch
    ].flatten.compact.join("\n")+"\n"
  end

  def verses(start, finish)
    (start..finish).map{ |verse_number| verse(verse_number)}.join("\n")+"\n"
  end

  def sing
    verses(1, CRITTERS.size)
  end

  private

  CRITTERS = []

  class Critter

    def initialize(params)
      @name = params.fetch(:name)
      @prey = params.fetch(:prey) { nil }
      @when_eaten = params.fetch(:when_eaten) { nil }
      @chained_ending = params.fetch(:chained_ending) { nil }
      @special_message = params.fetch(:special_message) { nil }
    end

    def to_s
      @name
    end

    def down_the_hatch
      lines = [when_eaten]
      lines << prey_chain 
      lines.flatten
    end

    def when_eaten
      if @when_eaten.respond_to?(:call)
        @when_eaten.call(self)
      else
        @when_eaten.to_s unless @when_eaten.nil?
      end
    end

    def prey_chain
      if @special_message
        @special_message.call(self)
      else
        unless @prey.nil?
          [
            "She swallowed the #{self} to catch the #{@prey.chained_text}.",
            @prey.prey_chain
          ].flatten
        end
      end
    end

    def chained_text
      if @chained_ending
        "#{@name} #{@chained_ending}"
      else
        @name
      end
    end

  end

  FLY = Critter.new name: 'fly',
    special_message: ->(critter) { 
      "I don't know why she swallowed the #{critter}. Perhaps she'll die." }
  CRITTERS << FLY

  SPIDER = Critter.new name: 'spider',
    prey: CRITTERS.last,
    when_eaten: "It wriggled and jiggled and tickled inside her.",
    chained_ending: "that wriggled and jiggled and tickled inside her"
  CRITTERS << SPIDER

  BIRD = Critter.new name: 'bird',
    prey: CRITTERS.last,
    when_eaten: ->(critter) {
      "How absurd to swallow a #{critter}!" }
  CRITTERS << BIRD

  CAT = Critter.new name: 'cat',
    prey: CRITTERS.last,
    when_eaten: ->(critter) {
      "Imagine that, to swallow a #{critter}!" }
  CRITTERS << CAT

  DOG = Critter.new name: 'dog',
    prey: CRITTERS.last,
    when_eaten: ->(critter) {
      "What a hog, to swallow a #{critter}!" }
  CRITTERS << DOG

  GOAT = Critter.new name: 'goat',
    prey: CRITTERS.last,
    when_eaten: ->(critter) {
      "Just opened her throat and swallowed a #{critter}!" }
  CRITTERS << GOAT

  COW = Critter.new name: 'cow',
    prey: CRITTERS.last,
    when_eaten: ->(critter) {
      "I don't know how she swallowed a #{critter}!" }
  CRITTERS << COW

  HORSE = Critter.new name: 'horse',
    special_message: ->(critter) { "She's dead, of course!" }
  CRITTERS << HORSE

end
