# Homework 1, part 3

def anagram?(str1,str2)
  array1 = str1.downcase.split(//)
  array2 = str2.downcase.split(//)
  array1.sort!
  array2.sort!
  if array1 == array2 then
    true
  else
    false
  end
end

def combine_anagrams(words)
  result = []
  while words != [] do
    cmp_word = words.shift
    current_anagram_array = []
    current_anagram_array << cmp_word
    words.delete_if do |word|
      if anagram?(word,cmp_word) then
        current_anagram_array << word
        true
      else
        false
      end
    end
    result << current_anagram_array
  end
  result
end
