class Anagram

  def initialize(word)
    @word = word.downcase
    @word_hash = Hash.new(0)
    word.downcase.split('').sort.each do |letter|
      @word_hash[letter] += 1
    end
  end

  def match(word_list)
    anagrams = []
    word_list.each do |word|
      if @word != word.downcase
        anagram_hash = Hash.new(0)
        word.downcase.split('').sort.each do |letter|
          anagram_hash[letter] += 1
        end
        if @word_hash == anagram_hash
          anagrams << word
        end
      end
    end
    anagrams
  end
end
