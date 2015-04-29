class Anagram
  def initialize anagram
    @anagram = anagram
  end

  def match list
    result = []
    words = list.map { |i| i.downcase }
    words.each_with_index do |word, idx|
      next if word == @anagram.downcase
      result << list[idx] if @anagram.downcase.chars.sort == word.chars.sort
    end
    result
  end
end
