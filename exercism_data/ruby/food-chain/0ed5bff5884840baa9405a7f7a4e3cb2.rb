class FoodChainSong
  def verse(n)
    Verse.new(n).to_s
  end

  def verses(from, to)
    Verse.verses(from, to).push('').join("\n")
  end

  def sing
    Verse.verses.push('').join("\n")
  end
end

class Verse
  ANIMAL_ELABORATIONS = {
    fly: "I don't know why she swallowed the fly. Perhaps she'll die.",
    spider: 'It wriggled and jiggled and tickled inside her.',
    bird: 'How absurd to swallow a bird!',
    cat: 'Imagine that, to swallow a cat!',
    dog: 'What a hog, to swallow a dog!',
    goat: 'Just opened her throat and swallowed a goat!',
    cow: "I don't know how she swallowed a cow!",
    horse: "She's dead, of course!"
  }

  def self.animals
    ANIMAL_ELABORATIONS.keys
  end

  def self.elaborations
    ANIMAL_ELABORATIONS.values
  end

  def self.verses(from = 1, to = elaborations.length)
    from.upto(to).map(&method(:new))
  end

  def initialize(n)
    @animals = self.class.animals.slice(0, n)
    @animal = @animals.last
  end

  def elaboration(animal = @animal)
    ANIMAL_ELABORATIONS[animal]
  end

  def to_s
    ["I know an old lady who swallowed #{an_animal}."]
      .push(elaboration)
      .push(*chasers)
      .push('')
      .join("\n")
  end

  private

  def an_animal
    # Not really necessary, here for grammatical completeness.
    "a#{'n' if @animal[/\A[aeiou]/i]} #{@animal}"
  end

  def first_or_last?
    self.class.animals.values_at(0, -1).include?(@animal)
  end

  def chasers
    return [] if first_or_last?

    @animals.reverse.each_cons(2)
      .map { |chaser, chased| chaser_line(chaser, chased) }
      .push(self.class.elaborations.first)
  end

  def chaser_line(chaser, chased)
    # Handle the "spider line", which is of a different form to the rest.
    if @animals.index(chased) == 1
      chased = elaboration(chased).sub(/\w+/, "#{chased} that").chomp('.')
    end
    "She swallowed the #{chaser} to catch the #{chased}."
  end
end
