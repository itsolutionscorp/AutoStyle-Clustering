# Software Engineering for SaaS
# Homework Number 1, Part 3
# Submitted by Tina Kashef
# Version 12-06-04 13:08

def combine_anagrams(words)

  # First create a hashtable where:
  # Keys are alphabetically-sorted lowercase anagrams of each word 
  # Values are arrays with the list of all words that are anagrams of the key
  h=Hash.new
  words.each do |word|
    base=word.downcase.chars.sort.join
    if h.key?(base) then
      h[base]=h[base]<<word
    else
      h[base]=[word]
    end
  end

  # Next string the anagram lists together to produce requested output
  ans=[]
  h.each {|key,value| ans=ans<<value}

  return ans

end
