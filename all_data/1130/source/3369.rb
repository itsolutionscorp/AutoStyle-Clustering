def combine_anagrams(words)

  i = 0
  words_a = Hash[words.map {|elt| [i+=1, elt.to_s.downcase.chars.sort.join]}]
  i = 0
  words_b = Hash[words.map {|elt| [i+=1, elt]}]

  words_c = Hash.new

  words_a.each do |key, value|
    if (words_c[value] == nil)
      words_c[value] = [words_b[key]]
    else
      words_c[value] = words_c[value] + [words_b[key]]
    end
  end

  anagrams = []

  words_c.each do |key, value|
    anagrams = anagrams + [value]
  end

  return anagrams

end
