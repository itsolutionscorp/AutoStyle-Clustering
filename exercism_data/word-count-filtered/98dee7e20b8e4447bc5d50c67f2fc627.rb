class Phrase

  SPECIAL_CHARS_REGEX = /[!&@^$%:;\.]/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.scan(/[\w']+/).each_with_object(Hash.new(0)) do |word, count|
      trimmed_word =word.gsub(SPECIAL_CHARS_REGEX, '').downcase
      count[trimmed_word] +=1
    end
  end

end
