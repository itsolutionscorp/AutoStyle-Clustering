class Anagram
  def initialize(subject)
    @subject = subject
  end
  
  def match(candidates)
    candidates.select { |candidate| anagram?(Anagram.new(candidate)) }
  end

  def subject
    @subject.downcase
  end

  def alphagram
    subject.chars.sort.join
  end

  def anagram?(candidate)
    self.subject != candidate.subject && self.alphagram == candidate.alphagram
  end
end
