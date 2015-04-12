class Phrase

  def initialize sentence
    @sentence = sentence
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, result|
      result[word] += 1
    end
  end

private
  def words
    @sentence.downcase.scan(%r{\w+})
  end

end
