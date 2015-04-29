class Words
  attr_reader :statement

  def initialize(phrase)
    @statement = phrase.downcase
  end

  def count
    word_list(statement).inject({}) do |memo, word|
      memo[word] = breakdown_phrase(statement).count(word)
      memo
    end
  end

private
  def word_list(words)
    breakdown_phrase(words).uniq
  end

  def breakdown_phrase(words)
    words.gsub(/[!&@$%^:,]/,'').split
  end
end
