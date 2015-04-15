class Words
  attr_reader :statement

  def initialize(phrase)
    @statement = phrase.downcase
  end

  def count
    word_list(statement).inject({}) do |memo, word|
      memo[word] = process_phrase(statement).count(word)
      memo
    end
  end

private
  def word_list(words)
    process_phrase(words).uniq
  end

  def process_phrase(words)
    words.scan(/[a-z0-9 ]/).join.split
  end
end
