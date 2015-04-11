class Anagram
  def initialize word
    @word = word.downcase
  end

  def match list
    list.select { |comparison| anagram?(comparison) }
  end
  
  private
  def anagram? comparison 
    @word.chars.sort == comparison.downcase.chars.sort
  end
end
