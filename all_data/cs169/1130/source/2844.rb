

def combine_anagrams(words)
  anagram_hash={}
  anagram_list=[]
  words.each do |word|
    ordered=word.downcase.chars.sort.join
    if anagram_hash.has_key?(ordered)
      anagram_hash[ordered]<<word
    else
      anagram_hash[ordered]=[word]
    end
  end
  anagram_hash.each_value { |value| anagram_list << value }
  anagram_list
end



