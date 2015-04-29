class Anagram

  def initialize(anagram)
    @anagram = anagram
  end

  def match(word_list)
    result = []
    word_list.collect do |word|
      if word.downcase == @anagram.downcase
      elsif first_do(word) == first_do(@anagram)
        result << word
      end
    end
    result
  end

  private
  
  def first_do(word)
    word.downcase.chars.sort
  end
end
