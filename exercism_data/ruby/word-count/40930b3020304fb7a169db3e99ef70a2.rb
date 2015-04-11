class Phrase
  def initialize(text)
    normalize_text = normalize(text)
    @words = to_array(normalize_text)
  end

  def word_count
    return @words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end

  private

    def to_array(text)
      text.split(/\W+/)
    end

    def normalize(text)
      text.downcase
    end
end
