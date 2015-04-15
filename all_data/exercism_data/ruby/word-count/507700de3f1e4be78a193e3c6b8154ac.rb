class Phrase
  def initialize(phrase_string)
    @phrase_string = phrase_string
  end


  def word_count
    @phrase_string.scan(/\w+/).each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end
end
