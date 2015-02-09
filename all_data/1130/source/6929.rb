def combine_anagrams(words)

  # array of Anagrams where each element is an array of words
  anagrams = Array.new

  # For each word, we look to see if there are any anagrams in our array,
  # if we find a match, we add it to the word array,
  # if not, then we create a new word array to store the value
  words.each do |w|
    
    bFound = false

    anagrams.each do |a|
      if a[0].downcase.chars.sort == w.downcase.chars.sort then
        a << w
        bFound = true
        break
      end
    end

    # if not found then get the size of the current list
    # and add a new array structure and populate it with our word
    if bFound == false then
      i = anagrams.length
      anagrams[i] = Array.new
      anagrams[i] << w
    end
  end
  return anagrams
end

