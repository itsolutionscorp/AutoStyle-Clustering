def combine_anagrams(words)
  def anagram?(x, y)
    (x.downcase.split("").sort == y.downcase.split("").sort)
  end
  anagrams = []
  flat = words.flatten
  flat.cycle do |word|
    (anagrams << [flat.delete(word)])
    anagrams.each_index do |index|
      flat.each do |w|
        (anagrams[index] << flat.delete(w)) if anagram?(anagrams[index][0], w)
      end
    end
  end
  anagrams
end