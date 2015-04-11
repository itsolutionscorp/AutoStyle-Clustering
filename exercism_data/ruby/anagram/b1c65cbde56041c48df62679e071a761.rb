class Anagram
  def initialize(anagram)
    @anagram = anagram
  end

  def match(words)
    anagram_down = @anagram.downcase
    @words_hash = words.each_with_object(Hash.new []) do |word, hash|
      word_down = word.downcase
      hash[word_down.chars.sort] += [word] unless word_down == anagram_down
    end

    @words_hash[anagram_down.chars.sort]
  end

end
