class Anagram
  def initialize(subject)
    @subject = subject
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private
  attr_reader :subject
  
  def normalized_subject
    @normalized_subject ||= normalize(subject)
  end

  def anagram?(word)
    normalized_subject.eql?(normalize(word))
  end

  def normalize(word)
    word.downcase.chars.sort
  end
end
