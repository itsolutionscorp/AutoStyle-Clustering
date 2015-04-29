class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    find_words(normalize(@phrase)).each_with_object(Hash.new(0)) do |word, words_map|
      words_map[word] += 1
    end
  end

  private

  def normalize phrase
    phrase.downcase
  end

  def find_words phrase
    phrase.scan(/['\w]+/)
  end
end
