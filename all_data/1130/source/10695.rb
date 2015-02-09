# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  word_hash={1 => [2,3]}
  words.each do |word|
    word_key=word.downcase.split(//).sort.join
     a=word_hash[word.downcase.split(//).sort.join]
     a=[a]+[word]
     a=a.flatten
     a=a.delete_if{|q| q==nil}
     word_hash[word.downcase.split(//).sort.join]=a     
  end
  word_hash.delete(1)
  anagrams_ar=[]
  word_hash.each {|key,value| anagrams_ar+=[value]}
  anagrams_ar
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])