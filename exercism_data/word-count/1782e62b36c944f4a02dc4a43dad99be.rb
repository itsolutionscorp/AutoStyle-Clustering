class Phrase < Struct.new(:phrase)
  SEPARATOR = /\W+/.freeze

  def word_count
    phrase
      .split(SEPARATOR)
      .group_by(&:downcase)
      .reduce({}) { |counts, (word, group)|
        counts.merge(word => group.size)
      }
  end
end
