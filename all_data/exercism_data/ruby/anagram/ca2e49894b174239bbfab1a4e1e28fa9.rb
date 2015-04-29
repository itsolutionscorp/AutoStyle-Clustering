class Anagram
  def initialize(word)
    @word  = word.downcase
    @chars = @word.chars.sort
  end

  def match(list)
    list.select { |candidate| anagram?(candidate.downcase) }
  end

  private
  def anagram?(candidate)
    return false if candidate == @word
    candidate.chars.sort == @chars
  end
end
