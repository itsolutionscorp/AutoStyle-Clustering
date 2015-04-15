module Enumerable
  def map_cons(n)
    result = []
    each_cons(n) do |items|
      result << yield(items)
    end
    result
  end
end

class Proverb
  attr_reader :nouns, :qualifier

  def initialize(*nouns, qualifier: qualifier)
    @nouns = nouns
    @qualifier = qualifier
  end

  def two_at_a_time(array)
    array.map_cons(2) do |one,two|
      yield one, two
    end
  end

  def verses
    two_at_a_time nouns do |one, two|
      "For want of a #{one} the #{two} was lost."
    end
  end

  def adverb
    qualifier + ' ' if qualifier
  end

  def ending
    "And all for the want of a #{adverb}nail."
  end

  def proverb
    (verses << ending).join("\n")
  end

  def to_s
    proverb
  end
end
