def combine_anagrams(words)
  out = []
  hashAnagrams = {}
  words.each do |w|
    wSorted = sort_word_chars(w)
    if hashAnagrams.has_key?(wSorted) then
      (hashAnagrams[wSorted] << w)
    else
      hashAnagrams[wSorted] = [w]
    end
  end
  hashAnagrams.each { |wSorted, arr| (out << arr) }
  out
end