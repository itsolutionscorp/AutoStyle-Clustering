class Phrase
  def initialize(source_phrase)
    @phrase = source_phrase
  end

  def word_count
      words.each_with_object({}) do |word, obj|
        downcased_word = word.downcase

        if obj.has_key?(downcased_word)
          obj[downcased_word] += 1
        else
          obj[downcased_word] = 1
        end
      end
  end

  private
  def words
    @phrase.split(/[^\w]+/)
  end
end
