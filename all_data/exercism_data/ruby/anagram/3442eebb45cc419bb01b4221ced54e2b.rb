class Anagram
  def initialize word
    @word = word.downcase
  end

  def match list
    list.each_with_object([]) do |comparison, anagrams|
      anagrams << comparison if anagram?(comparison)
    end
  end
  
  private
  def anagram? comparison 
    @word.split('').sort == comparison.downcase.split('').sort
  end

end
