class Words
  attr_reader :statement

  def initialize(phrase)
    @statement = phrase
  end

  def count
    word_list.inject({}) do |memo, word|
      memo[word] = process_phrase.count(word)
      memo
    end
  end

private
  def word_list
    process_phrase.uniq
  end

  def process_phrase
    normalize(statement)
  end

  def normalize(word)
    word.downcase.scan(/[a-z0-9 ]/).join.split
  end
end
