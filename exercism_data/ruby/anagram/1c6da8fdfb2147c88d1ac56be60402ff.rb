class Anagram
  def initialize word
    @word = word
  end

  def match list
    list.select { |comparison| anagram?(comparison) }
  end
  
  private
  def anagram? comparison 
    @word.downcase.chars.sort == comparison.downcase.chars.sort
  end
end
