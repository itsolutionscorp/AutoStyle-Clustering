def combine_anagrams(words)
  hash = {}
  result = []
  words.each do |word|
    key = word.downcase.split('').sort.join
    if hash[key]
      # add new word into array
      hash[key].push(word)
    else
      # create array with first word
      hash[key] = [word]
    end
  end

  hash.each do |key, value|
    result.push value
  end

  result
end