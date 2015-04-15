class Words
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end

  def count
    count_word_occurrences expression
  end

  private

  def count_word_occurrences(text)
    results = Hash.new(0)
    list_of_words(text).each {|word| results[word] += 1}

    results
  end

  def list_of_words(text)
    normalize(text).split
  end

  def normalize(text)
    text.to_s.gsub(/\W+/, ' ').downcase
  end
end
