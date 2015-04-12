class Words
  attr_reader :words

  def initialize(words)
    @words = words.downcase
  end

  def count
    word_list(words).inject({}) do |memo, word|
      memo[word] = discard_punctuation(words).split.grep(word).size
      memo
    end
  end

private
  def word_list(words)
    discard_punctuation(words).split.uniq
  end

  def discard_punctuation(words)
    words.gsub(/[!&@$%^:,]/,'')
  end
end
