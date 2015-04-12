class Phrase

  def initialize(input)
    @sentence = input
  end

  def word_count
    @sentence.scan(/\w+/).each_with_object(Hash.new(0)) { |word, count| count[word.downcase] += 1 }
  end

end
