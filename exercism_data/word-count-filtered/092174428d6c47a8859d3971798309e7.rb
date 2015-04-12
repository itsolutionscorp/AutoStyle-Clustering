class Phrase
  def initialize(words)
    @words = words
  end

  def word_count
    counts = {}

    @words.split(/\W/).delete_if(&:empty?).each do |word|
      word = word.downcase
      if counts[word]
        counts[word] += 1
      else
        counts[word] = 1
      end
    end

    counts
  end
end
