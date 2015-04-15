class Anagram
  attr_reader :subject

  def initialize(subject)
    @subject = subject
  end

  def match(guinea_pigs)
    guinea_pigs.select {|word| anagram?(word) }
  end

  private

  def alphagram(word)
    word.downcase.chars.sort.join
  end

  def anagram?(word)
    alphagram(subject) == alphagram(word) && subject.downcase != word.downcase
  end
end
