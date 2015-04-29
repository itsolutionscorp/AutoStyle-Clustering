class Phrase
  attr_reader :expression

  def initialize(expression)
    @expression = expression
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def words
    expression.downcase.scan(/[\w']+/)
  end
end
