class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    find_words(normalize(@phrase)).each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

  private

  def normalize phrase
    phrase.downcase.strip
  end

  def find_words phrase
    phrase.scan(/['\w]+/)
  end
end
