class Anagram
  def initialize(anagram)
    @anagram = anagram.downcase
  end

  def match(words_to_match)
    matching = []
    words_to_match.each do |word|
      if @anagram.split('').sort == word.downcase.split('').sort && @anagram != word.downcase
        matching << word
      end
    end
    matching
  end
end
