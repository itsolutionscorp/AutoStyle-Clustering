class Phrase

  SPECIAL_CHARS_REGEX = /[!&@^$%:;\.]/

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.scan(/[\w']+/).each_with_object(Hash.new(0)) {|word, count| count[trimmed_word =word.gsub(SPECIAL_CHARS_REGEX, '').downcase] +=1 }
  end

end
