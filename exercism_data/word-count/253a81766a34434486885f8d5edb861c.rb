class Words
  attr_reader :statement

  def initialize(phrase)
    @statement = phrase
  end

  def count
    word_list.inject({}) do |memo, word|
      memo[word] = normalize.count(word)
      memo
    end
  end

private
  def word_list
    normalize.uniq
  end

  def normalize
    statement.downcase.scan(/\w+/)
  end
end
