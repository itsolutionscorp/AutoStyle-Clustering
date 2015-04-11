class Phrase

  def initialize(phrase)
    phrase = normalize_case(phrase)
    @phrase = clean_punctuation(phrase)
  end

  def word_count
    words.
      group_by { |word| word }.
      map { |word, group| [word, group.size] }.
      to_h
  end

  private

  def words
    @phrase.split(/\s+|,\s?/)
  end

  def normalize_case(phrase)
    phrase.downcase
  end

  def clean_punctuation(phrase)
    phrase.gsub(/[:!&@$%\^&\.]/, '')
  end
  
end
