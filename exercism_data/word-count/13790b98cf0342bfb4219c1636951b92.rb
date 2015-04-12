class Phrase
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    normalized_words.each_with_object(Hash.new(0)) do |w, c|
      c[w] = c[w] + 1
    end
  end

  private
  def normalized_words
    @sentence.downcase.scan(/[[:word:]']+/)
  end
end
