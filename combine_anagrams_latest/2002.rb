def combine_anagrams(words)
#  <YOUR CODE HERE>
  words_hash = Hash.new {|h,k| h[k] = []}

  words.each do |n|
    word = n.downcase.split('').sort().join
    words_hash[word] << n
  end

  return words_hash.values
end