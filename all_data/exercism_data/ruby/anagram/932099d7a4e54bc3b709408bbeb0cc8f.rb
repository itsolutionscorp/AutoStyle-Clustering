class Anagram
  def initialize(anagram)
    @original = anagram
    @chars    = anagram.downcase.split('').sort
  end

  def match(words)
    words.each_with_object([]) do |word, list|
      list << word if word.downcase.split('').sort == @chars &&
                      word.downcase != @original.downcase
    end
  end
end
