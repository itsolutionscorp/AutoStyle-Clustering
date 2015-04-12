class Phrase
  def initialize text
    @text = text
  end

  def sanitized_text
    @text.downcase.gsub /[^a-z0-9']/, " "
  end

  def words
    sanitized_text.split " "
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
  end
end
