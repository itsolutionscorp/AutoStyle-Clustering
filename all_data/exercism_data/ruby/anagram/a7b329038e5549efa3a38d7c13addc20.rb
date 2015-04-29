class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(word_list)
  result = []

    word_list.each do |word|
      if word.downcase == @anagram.downcase
      else
        if prep(word) == prep(@anagram)
        result << word
        end
      end
    end

  result
  end

  private

  def prep(word)
    word.downcase.split('').sort
  end

end
