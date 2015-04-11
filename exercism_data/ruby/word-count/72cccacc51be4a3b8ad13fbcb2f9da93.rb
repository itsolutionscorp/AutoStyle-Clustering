class Phrase
  REGEX_WORD = /[\w']+/

  def initialize(text)
    @text = text
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end


  private

  def words
    @text.scan(REGEX_WORD).map(&:downcase)
  end
end
