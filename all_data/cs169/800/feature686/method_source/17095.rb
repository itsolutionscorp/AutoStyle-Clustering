def combine_anagrams(words)
  array_full = Array.new
  words.each do |word|
    array = Array.new
    (array << word)
    words.each do |word_inner|
      if word.split(//).sort.join.===(word_inner.split(//).sort.join) then
        (array << word_inner) if (not array.include?(word_inner))
      end
    end
    (array_full << array.sort) if (not array_full.include?(array.sort))
  end
  return array_full
end