class Words
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def count
    word_list(words).inject({}) do |memo, word|
      memo["#{word}"] = lowercase_words_only(words).split.grep(word).size
      memo
    end
  end

private
  def word_list(words)
    lowercase_words_only(words).split.uniq
  end

  def lowercase_words_only(words)
    words.gsub(/[!&@$%^:,]/,'').downcase
  end
end
