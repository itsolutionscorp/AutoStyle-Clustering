class Phrase

  SPECIAL_CHARS_REGEX = Regexp.new("[\!\&\@\$\%\^\:\;\.]")

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @phrase.split(/,|\s/).each_with_object(Hash.new(0)) do |word, count|
      trimmed_word =word.gsub(SPECIAL_CHARS_REGEX, '').downcase
      count[trimmed_word] +=1 unless trimmed_word.empty?
    end
  end

end
