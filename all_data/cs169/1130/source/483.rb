###################
### Homework 01 ###
###   Part 03   ###
###################


# 3

def combine_anagrams(words)
  clusters = {}
  
  words.each do |word|
    key = word.downcase.scan(/\w/).sort.join
    if clusters.has_key? key
      clusters[key].push word
    else
      clusters[key] = []
      clusters[key].push word
    end
  end
  
  clusters.values
end