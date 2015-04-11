class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = extract_words(phrase)
  end

  def word_count()
    counts = Hash.new
    @words.each do |word|
      if counts.has_key? word
        counts[word] = counts[word] + 1
      else
        counts[word] = 1
      end
    end
    return counts
  end

  private

  def extract_words(phrase)
    phrase.tr('A-Z', 'a-z')                         # Normalize case
          .split(/[ ,\.!?:;]/)                      # Split on whitespace and punctuation
          .select { |word| !word.nil? }             # Filter out nils
          .select { |word| word.match(/[a-z0-9]/) } # Filter out non-alphanumeric characters
  end
end
