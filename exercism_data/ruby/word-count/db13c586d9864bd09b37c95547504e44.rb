class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.scan(/\w+/).each_with_object(Hash.new(0)) do |word, count|
      word = normalize_case(word)
      count[word] = count[word] + 1 unless word.empty?
    end
  end

  def normalize_case(word)
    word.downcase
  end
end
