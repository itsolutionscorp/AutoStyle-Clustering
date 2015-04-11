class Anagram
  attr_reader :subject

  def initialize(input)
    @subject = input.downcase
  end

  def match(candidates)
    candidates.find_all do |candidate|
      anagram?(candidate)
    end
  end

  private

  def anagram?(candidate)
    !duplicate?(candidate) && rearranged?(candidate)
  end

  def duplicate?(word)
    word.downcase == subject
  end

  def rearranged?(word)
    word.downcase.chars.sort == subject.chars.sort
  end

end
