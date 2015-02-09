#SaaS Programming assign #1-3

#returns Array with sub-arrays for each anagram group
def combine_anagrams( words )
  # <YOUR CODE HERE>
  groups = Hash.new { |hash, key| hash[key] = [] }
  words.each { |word| groups[alphabetize( word )] << word }
  groups.values
end


def alphabetize( word ) 
  word.downcase.split( "" ).sort.join
end
