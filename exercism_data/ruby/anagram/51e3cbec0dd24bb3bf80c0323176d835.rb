class Anagram

  def initialize(anagram)
    @anagram = anagram
  end

  def match(word_list)
    result = []

    word_list.each do |word|
      if @anagram == word.downcase
        nil
      elsif prep(@anagram) == prep(word)
        result << word
      else
        result
      end
    end
    result
  end

  private

  def prep(word)
    word.downcase.chars.sort
  end
  
end
