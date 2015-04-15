class Anagram

  def initialize(subject)
    @subject = subject.downcase
  end

  def match(words)
    words.select { | word | anagram?(word) } 
  end

  private
    def anagram?(word)
      word.downcase.chars.sort == @subject.chars.sort && word.downcase != @subject
    end
end
