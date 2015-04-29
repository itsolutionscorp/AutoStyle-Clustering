class Words
  attr_reader :input

  def initialize(phrase)
    @input = phrase.downcase
  end

  def count
    word_list(input).inject({}) do |memo, word|
      memo[word] = phrase_breakdown(input).count(word)
      memo
    end
  end

private
  def word_list(words)
    phrase_breakdown(words).uniq
  end

  def phrase_breakdown(words)
    words.gsub(/[!&@$%^:,]/,'').split
  end
end
