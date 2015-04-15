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

  private

  def duplicate?(contender)
    subject.downcase == contender.downcase
  end

  def character_match?(contender)
    fingerprint(subject) == fingerprint(contender)
  end

  def fingerprint(word)
    word.downcase.chars.sort
  end

end
