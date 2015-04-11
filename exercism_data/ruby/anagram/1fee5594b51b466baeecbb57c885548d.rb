class Anagram

  attr_reader :subject
  
  def initialize(subject)
    @subject = subject
  end

  def match(contenders)
    contenders.find_all { |contender| anagram?(contender) }
  end

  def anagram?(contender)
    character_match?(contender) and not duplicate?(contender) 
  end

  def duplicate?(contender)
    subject.downcase == contender.downcase
  end

  def character_match?(contender)
    characters(subject) == characters(contender)
  end

  def characters(word)
    word.downcase.chars.sort
  end

end
