# Immutable inside out.
class Phrase < BasicObject
  SEPARATOR = /\W+/.freeze

  attr :input

  def initialize(input)
    @input = input
  end

  def word_count
    input
      .split(SEPARATOR)
      .group_by(&:downcase)
      .reduce({}) { |counts, (word, group)|
        counts.merge(word => group.size)
      }
  end
end
