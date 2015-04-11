class Anagram

  def initialize word
    @word = word
    @compareword = downcase_and_sort_characters word
  end

  def match anagrams
    raise NameError, "Format should be in array []" if anagrams.class != Array
    result = []
    anagrams.each do |anagram|
      if @word.eql? anagram
        break
      end
      if @compareword.eql?(downcase_and_sort_characters anagram)
        result << anagram
      end
    end
    result
  end

  private

  def downcase_and_sort_characters word
    word.downcase.chars.sort.join
  end
end
