class Phrase
  def initialize(source_phrase)
    @phrase = source_phrase
  end

  def word_count
      words.each_with_object({}) do |word, obj|
        downcased_word = word.downcase

        obj[downcased_word] ||= 0
        obj[downcased_word] += 1
      end
  end

  private
  def words
    @phrase.scan(/\w+/)
  end
end
