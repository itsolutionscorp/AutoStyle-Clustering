class Anagram

  attr_reader :subject

  def initialize(word)
    @subject = word
  end

  def match(list)
    list.select { |w| anagram?(w)}
  end

  def anagram?(w)
    normalized(w) == normalized(subject) && w.downcase != subject.downcase
  end

  def normalized(word)
    word.downcase.chars.sort 
  end

end
