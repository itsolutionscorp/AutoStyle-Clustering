class Anagram
 
  def initialize(word)
    @word = sort(word)
  end

  def match(input)
    input.select { |candidate| sort(candidate) == @word }
  end

  private
    def sort(word)
      word.chars.sort
    end 
end
