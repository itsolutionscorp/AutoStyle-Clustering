class Anagram
  def initialize(word)
    @word = word.downcase
    @chars = @word.chars.sort
  end

  def match(list)
    list.select do |x|
      x = x.downcase 
      @word != x && @chars == x.chars.sort
    end
  end
end
