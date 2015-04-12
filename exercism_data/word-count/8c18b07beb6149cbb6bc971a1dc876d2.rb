class Phrase

  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count
    score = {}
    @words.each do |word|
      score[word] = @words.grep(word).count
    end
    score
  end

  private

  def extract_words(phrase)
    phrase.split(/\W/).reject do |word|
      word.empty?
    end.map do |word|
      word.downcase
    end
  end
end
