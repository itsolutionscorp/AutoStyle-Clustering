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
    statement.lowercase_words_and_numbers_array
  end
end



class String
  def lowercase_words_and_numbers_array
    downcase.scan(/[a-z0-9 ]/).join.split
  end
end
