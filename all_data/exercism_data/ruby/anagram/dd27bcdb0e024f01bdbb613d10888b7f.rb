class Anagram
  def initialize(anagram)
    @anagram = anagram.downcase.chars
  end

  def match(words)
    words.each_with_object([]) do |word, obj|
      chars = word.downcase.chars
      obj << word if (chars.sort == @anagram.sort) && (chars != @anagram)
    end
  end
end
