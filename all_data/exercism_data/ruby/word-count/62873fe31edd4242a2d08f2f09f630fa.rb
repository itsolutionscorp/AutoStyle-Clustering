class Phrase
  def initialize(sentence)
    @words = sentence.split /[^A-Za-z\d'']+/
  end

  def word_count
    @words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end
end
