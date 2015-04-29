class Words
  attr_reader :input

  def initialize(words)
    @input = words.downcase
  end

  def count
    word_list(input).inject({}) do |memo, word|
      memo[word] = discard_punctuation(input).split.count(word)input
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
