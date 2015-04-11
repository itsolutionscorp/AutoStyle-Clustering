class Words
  attr_reader :words

  def initialize(words)
    @words = words.downcase
  end

  def count
    word_list(words).inject({}) do |memo, word|
      memo[word] = lowercase_words_only(words).split.grep(word).size
      memo
    end
  end

private
  def word_list(words)
    lowercase_words_only(words).split.uniq
  end

  def discard_punctuation(words)
    words.gsub(/[!&@$%^:,]/,'')
  end
end

# `lowercase_words_only` sounds like it would discard words
# that aren't lowercase, but what you really want is to normalize
# the words and then count them. Rename the method to be more
# expressive of what it actually dose. You never really need
# the original `@words`. Could you do some preprocessing in
# `initialize` to get what you actually want? Are you familiar
# with `Array#count`?
