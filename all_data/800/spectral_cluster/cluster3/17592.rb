def combine_anagrams(words)
  grams = {}
  words.each do |word|
    sorted = word.downcase.split('').sort
    if grams.has_key? sorted
      grams[sorted].push(word)
    else
      grams[sorted] = [word]
    end
  end
  grams.inject([]) { |arr, group| arr.push group[1] }
end