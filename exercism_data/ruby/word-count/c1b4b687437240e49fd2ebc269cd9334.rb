class Phrase
  REGEX_WORD = /[A-Za-z0-9\']*/

  def initialize(text)
    @text = text
  end

  def word_count
    word_array.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end


  private

  def word_array
    @text.scan(REGEX_WORD).reject(&:empty?).map(&:downcase)
  end
end
