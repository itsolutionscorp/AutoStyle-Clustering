def combine_anagrams(words)
  out = Array.new(0)
  words.each do |word|
    anagram = Array.new(0)
    words.each do |w|
      if (w.downcase.split(//).sort == word.downcase.split(//).sort) then
        anagram.push(w)
      end
    end
    out.push(anagram)
  end
  return out.uniq.sort
end

