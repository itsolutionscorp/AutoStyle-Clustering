class Anagram
  def initialize(word)
    @word = word
  end

  def match(anagrams)
    match_it = Array.new
    word_anagrams = gen_anagrams
    anagrams.each do |w|
      if word_anagrams.include?(w)
        match_it << w
      end
    end
    match_it
  end

  def gen_anagrams
    @word.downcase.chars.permutation.map(&:join)
  end
end
