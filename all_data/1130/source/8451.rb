
# ------- Part 3 --------- #

# another way to do this is to use a regex since the order also doesnt matter in that case
def combine_anagrams(words)
  hs = Hash.new([])
  words.each { |word|
#    hs[word.lowercase.split(//).sort.join] += [word]
    hs[word.downcase.chars.sort.join] += [word]
  }
  hs.values
end
