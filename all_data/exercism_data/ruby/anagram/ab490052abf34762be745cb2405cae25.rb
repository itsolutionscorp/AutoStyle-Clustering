class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    result = []
    list.each do |word|
      if word.downcase == @word.downcase
      elsif prep(word) == prep(@word)
        result << word
      end
    end
    result
  end

  private

  def prep(word)
    word.downcase.chars.sort
  end
end
