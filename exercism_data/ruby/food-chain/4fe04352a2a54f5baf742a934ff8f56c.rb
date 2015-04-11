require 'forwardable'

class FoodChainSong
  def sing
    verses(1, 8)
  end

  def verses(start, ending)
    song = ""

    (start..ending).each { |number| song << verse(number) + "\n" }

    song
  end

  def verse(number)
    case number
    when 1
      FragmentsOne.new(number).to_s
    when 2
      FragmentsTwo.new(number).to_s
    when 3
      FragmentsThree.new(number).to_s
    when 4
      FragmentsFour.new(number).to_s
    when 5
      FragmentsFive.new(number).to_s
    when 6
      FragmentsSix.new(number).to_s
    when 7
      FragmentsSeven.new(number).to_s
    when 8
      "I know an old lady who swallowed a horse.\n" +
      "She's dead, of course!\n"
    end
  end
end

class Fragments
  extend Forwardable

  attr_reader :number, :animals

  def_delegators :animals,
                 :first_animal,
                 :second_animal

  def initialize(number)
    @number = number
    @animals = Animals.new
  end

  def to_s
    opening_stanza +
    medium_stanza +
    spider_stanza +
    ending_stanza
  end

  private

  def opening_stanza
    "I know an old lady who swallowed a #{second_animal(number)}.\n" +
    second_verse
  end

  def medium_stanza
    stanza = ""

    (number - 1).downto(3) { |n| stanza << animal_verse(n) }

    stanza
  end

  def spider_stanza
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n" +
    "She swallowed the spider to catch the fly.\n"
  end

  def ending_stanza
    "I don't know why she swallowed the fly. " +
    "Perhaps she'll die.\n"
  end

  def animal_verse(n)
    "She swallowed the #{first_animal(n)} to catch the #{second_animal(n)}.\n"
  end
end

class Animals
  def all
    %w[ fly spider bird cat dog goat cow horse ]
  end

  def first_animal(position)
    all[position]
  end

  def second_animal(position)
    all[position - 1]
  end
end

class FragmentsSeven < Fragments
  def second_verse
    "I don't know how she swallowed a cow!\n"
  end
end

class FragmentsSix < Fragments
  def second_verse
    "Just opened her throat and swallowed a goat!\n"
  end
end

class FragmentsFive < Fragments
  def second_verse
    "What a hog, to swallow a dog!\n"
  end
end

class FragmentsFour < Fragments
  def second_verse
    "Imagine that, to swallow a cat!\n"
  end
end

class FragmentsThree < Fragments
  def second_verse
    "How absurd to swallow a bird!\n"
  end
end

class FragmentsTwo < Fragments
  def to_s
    opening_stanza +
    ending_stanza
  end

  def second_verse
    "It wriggled and jiggled and tickled inside her.\n" +
    "She swallowed the spider to catch the fly.\n"
  end
end

class FragmentsOne < Fragments
  def to_s
    opening_stanza +
    ending_stanza
  end

  def second_verse
    ""
  end
end
