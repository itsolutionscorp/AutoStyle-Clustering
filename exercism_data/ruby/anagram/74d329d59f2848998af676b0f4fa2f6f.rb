class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word
  end

  def match(word_list)
    word_list.select { |word| anagram?(word) }
  end

  def anagram?(word)
    deconstructed(word) == deconstructed(subject) && word.downcase != subject.downcase
  end

  def deconstructed(word)
    word.chars.sort
  end

end
