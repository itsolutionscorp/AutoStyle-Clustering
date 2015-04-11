class PigLatin

  def self.translate(word)
    word.match(" ") ? translate_phrase(word) : translate_word(word)
  end

  private

  def self.translate_word(word)
    until word[0] =~ /[aeiou]/
      word[word.length] = word[0]
      word.slice!(0)
    end
    word += "ay"
  end

  def self.translate_phrase(phrase)
    phrase.split(" ").inject{|sum, word| sum + " " + translate_word(word)}
  end

end
