class Phrase
  def initialize(input)
    @input = input
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, result|
      result[word] += 1
    }
  end

  private
  def words
    @input.to_s.downcase.scan(/\w+/)
  end
end
