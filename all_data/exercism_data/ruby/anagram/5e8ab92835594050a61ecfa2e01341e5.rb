class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word
  end

  def match(word_list)
    word_list.select { |word| anagram?(word)}
  end

  def anagram?(word)
    normalized(word) == normalized(subject) && word.downcase != subject.downcase
  end

  def normalized(word)
    word.downcase.chars.sort 
  end

end
