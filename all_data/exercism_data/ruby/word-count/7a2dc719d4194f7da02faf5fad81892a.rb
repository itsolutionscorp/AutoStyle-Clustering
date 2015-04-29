class Phrase

  def initialize phrase
    @content = phrase
  end

  def word_count
    normalize(@content).each_with_object(Hash.new(0)) { |word, counter| counter[word] += 1 }
  end

  private

  def normalize phrase
    phrase.downcase.scan(/\w+/)
  end

end
