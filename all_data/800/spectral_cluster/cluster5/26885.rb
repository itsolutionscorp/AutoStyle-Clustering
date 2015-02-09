#Assigment1 anagrams - Juan Felipe Arango - 
def combine_anagrams(words)
  final_hash = words.inject({}) do |hash, word|
    hash[word.downcase.split('').sort] ||= []
    hash[word.downcase.split('').sort] << word
    hash
  end
  final_hash.values
end

