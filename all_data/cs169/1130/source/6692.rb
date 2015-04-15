def combine_anagrams(words)

  anagrams = []
  output   = []

  words.each do |word|
    sorted = word.downcase.split("").sort.join

    unless anagrams.include?(sorted)
      anagrams << sorted
    end

    i = anagrams.index(sorted)
    output[i] ||= []
    output[i] << word
  end

  output
end