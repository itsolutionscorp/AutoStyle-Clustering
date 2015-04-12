class Phrase
  def initialize text
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def words
    sanitised_text.downcase.split
  end

  def sanitised_text
    remove_punctuation_from(standardise_delimeter_in @text)
  end

  def remove_punctuation_from text
    text.gsub(/[^\w\s']/, '')
  end

  def standardise_delimeter_in text
    text.gsub(/,/, ' ')
  end
end
