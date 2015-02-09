def combine_anagrams(words)
  out = []
  words.each do |word|
    didmatch = false
    out.each do |compare|
      if compare[0] and (compare[0].downcase.split(//).sort == word.downcase.split(//).sort) then
        compare.push(word)
        didmatch = true
      end
    end
    out.push([word]) if (not didmatch)
  end
  return out
end