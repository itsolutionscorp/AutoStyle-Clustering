class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_count ={}
    words.each do |word|
      if word_count.has_key?(word)
        word_count[word] += 1
      else
        word_count[word] = 1
      end
    end
    word_count
  end

  private

  def words
    @phrase.split(/[\s,]+/).select { |word| word =~ /[A-Za-z0-9_]/ }.map { |word| word.delete('!&@$%^&:.').downcase }
  end
end
