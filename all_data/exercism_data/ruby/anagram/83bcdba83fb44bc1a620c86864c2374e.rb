class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(word_list)
    result = []
    word_list.each do |word|
      if word.downcase == @anagram.downcase

      elsif prep(word.downcase) == prep(@anagram.downcase)
        result << word
      end
    end

    result
  end

  private

  def prep(word)
    word.split('').sort
  end

end
