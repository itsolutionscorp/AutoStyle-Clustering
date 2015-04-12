class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= begin
                      counts = Hash.new { |hash, key| hash[key] = 0 }
                      phrase_as_cleaned_array.each { |word| counts[word.downcase] += 1 }
                      counts
                    end
  end

  private

  def phrase_as_cleaned_array
    @phrase.gsub(/[^a-zA-Z0-9]/, " ").split(/ +/)
  end
end
