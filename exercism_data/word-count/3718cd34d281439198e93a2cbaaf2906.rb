class Phrase

  def initialize(input)
    @sentence = input
  end

  def word_count
    sentence_array.each_with_object(Hash.new(0)) do |word, count| 
      count[word.downcase] += 1 
    end
  end

  private

  def sentence_array
    @sentence.scan(/\w+/)
  end

end
