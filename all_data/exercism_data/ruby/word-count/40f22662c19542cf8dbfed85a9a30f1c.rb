class Phrase
  def initialize(text)
    clean_text = remove_punctuation(text)
    normalize_text = normalize(clean_text)
    @words = to_array(normalize_text)
  end

  def word_count
    counts = Hash.new(0)
    @words.each { |word| counts[word] += 1 }
    counts
  end

  private

    def remove_punctuation(text)
      text.gsub(/(\W)/, " ")
    end

    def to_array(text)
      text.split(/[\s,]+/)
    end

    def normalize(text)
      text.downcase
    end
end
