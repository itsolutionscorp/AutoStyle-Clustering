class Phrase < Struct.new(:phrase)
  def word_count
    words.reduce({}) do |counts, word|
      normalized_word = normalize(word)
      counts[normalized_word] ||= 0
      counts[normalized_word] += 1
      counts
    end
  end

  private

  def words
    phrase.split(/\W+/)
  end

  def normalize(word)
    word.downcase
  end
end
