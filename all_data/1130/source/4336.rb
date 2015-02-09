def combine_anagrams(words)
  sets = {}
  words.each do |word|
    key = word.upcase.split(%r//).sort.join
    if not sets.has_key? key
      sets[key] = []
    end

    sets[key] << word
  end

  results = []
  sets.each_value {|value| results << value}

  results
end

