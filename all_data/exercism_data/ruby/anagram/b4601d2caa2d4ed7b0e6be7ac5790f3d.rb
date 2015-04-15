class Anagram

  attr_reader :subject
  
  def initialize(subject)
    @subject = subject
  end

  def match(contenders)
    contenders.find_all do |contender|
      contender if anagram?(contender)
    end
  end

  def anagram?(contender)
    character_match?(contender) and not duplicate?(contender) 
  end

  def duplicate?(contender)
    subject.downcase == contender.downcase
  end

  def character_match?(contender)
    character_group(subject) == character_group(contender)
  end

  def character_group(word)
    word.downcase.split('').group_by { |char| char }
  end

end
