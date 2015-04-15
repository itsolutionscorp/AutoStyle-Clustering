class Words
  attr_reader :word_list

  def initialize(expression)
    @word_list = list_of_words expression
  end

  def count
    word_list.each_with_object(Hash.new(0)) do |word, results|
      results[word] += 1
    end
  end

  private

  def list_of_words(text)
    normalize(text).split ' '
  end

  def normalize(text)
    text.to_s.gsub(/\W+/, ' ').downcase
  end
end
