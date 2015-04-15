Phrase = Struct.new(:phrase) do
  def word_count
    sanitized_words.each_with_object({}) do |word, acc|
      acc[word] ||= 0
      acc[word] += 1
    end
  end

  def sanitized_words
    phrase.downcase.gsub(/[^[:alnum:]\s']/, ' ').split(" ")
  end
end
