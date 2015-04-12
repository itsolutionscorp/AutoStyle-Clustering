class Phrase
  def initialize(phrase)
    @phrase = normalize(phrase)
  end

  def word_count
    @word_count ||= calculate_word_count
  end

  private
  def normalize(phrase)
    phrase.downcase
  end

  def calculate_word_count
    @phrase.scan(/\w+/).each_with_object(Hash.new(0)) do |word, dict|
      dict[word] += 1
    end
  end
end
