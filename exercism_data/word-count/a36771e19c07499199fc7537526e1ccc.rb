class Phrase
  def initialize sentence
    @sentence = sentence
  end

  def word_count
    normalized_words.each.inject(Hash.new(0)) do |c, w|
      c[w] = c[w] + 1
      c
    end
  end

  private
  def normalized_words
    @sentence.downcase.scan(/[[:word:]']+/)
  end
end
