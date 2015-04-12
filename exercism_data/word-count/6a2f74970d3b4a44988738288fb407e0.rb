class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    map = split
            .group_by { |word| word }
            .map      { |word, ocurrences| [word, ocurrences.size] }
    Hash[map]
  end

  private

  def split
    @words.downcase
          .split(/\W/)
          .reject { |word| word == "" }
  end
end
