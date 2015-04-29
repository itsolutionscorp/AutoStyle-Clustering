class Phrase < Struct.new(:phrase)
  def word_count
    sanitized_phrases.inject({}) do |acc, word|
      acc[word] ||= 0
      acc[word] += 1
      acc
    end
  end

  def sanitized_phrases
    phrase.downcase.gsub(/[^[:alnum:]\s']/, ' ').split(" ")
  end
end
