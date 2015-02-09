def combine_anagrams(words)
  word_hash=Hash.new
  words.each do |word|
    anagram=get_anagram(word)
    if word_hash.key?(anagram)
      word_hash[anagram]<< word
    else
      word_hash[anagram]=[word]
    end
  end
  words_anagram=[]
  word_hash.each do |key,word|
    words_anagram+=[word]
  end
 return words_anagram
end
def get_anagram(word)
  word_string=String.new
  word_arr=Array.new
  word_arr=word.split(//)
  word_arr=word_arr.sort
  word_arr.each do |s|
    word_string+=s
  end
return word_string
end
words=["cars","for",'potatoes','racs','four','scar','creams','scream']
anagram=combine_anagrams(words)
print anagram
