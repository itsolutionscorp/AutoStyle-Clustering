# @brief method combine_anagrams groups all the words which are anagrams together
# @note anagrams are words which has the same amount of characters, but in different orders e.g. 'cars' and 'scar'
#
# @param words the list of words
# @return an array of sub-arrays. In every sub-array is a group of anagrams.
#
def combine_anagrams(words)
  hash = Hash.new
  # traverse the word list
  words.each do |word|
    # convert the word into a sorted-character word e.g. 'cars' -> 'acrs'
    sorted_word = word.downcase.chars.sort.join

    # if the sorted_word is not the current key in the hash, initiate an empty-array value,
    # otherwise, append the word into the array value of the sorted_word key
    hash[sorted_word] = Array.new if !hash.include? sorted_word
    hash[sorted_word] << word
  end

  # retrieve the array value from the hash since the key (sorted word) is not what we are looking for
  result = Array.new
  hash.each_value { |word| result << word }
  return result
end