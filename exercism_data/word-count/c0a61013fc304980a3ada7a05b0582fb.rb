class Words
  attr_reader :word_list

  def initialize(expression)
    @word_list = list_of_words expression
  end

  def count
    count_word_occurrences
  end

  private

  def count_word_occurrences
    results = Hash.new(0)
    word_list.each {|word| results[word] += 1}

    results
  end

  def list_of_words(text)
    normalize(text).split ' '
  end

  def normalize(text)
    text.to_s.gsub(/\W+/, ' ').downcase
  end
end
