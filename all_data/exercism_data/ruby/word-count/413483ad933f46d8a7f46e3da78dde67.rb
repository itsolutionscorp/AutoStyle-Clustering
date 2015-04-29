class Phrase < Struct.new(:phrase)
  WORD_REGEXP = /[\w']+/

  def word_count
    words.each_with_object(Hash.new(0)) do |word, frequencies|
      frequencies[word] += 1
    end
  end

  private

  def words
    phrase.downcase.scan(WORD_REGEXP)
  end
end
