# Immutable inside out.
class Phrase < BasicObject
  SEPARATOR = /\W+/.freeze

  attr :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase
      .split(SEPARATOR)
      .group_by(&:downcase)
      .reduce({}) { |counts, (word, group)|
        counts.merge(word => group.size)
      }
  end
end
