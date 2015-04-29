
def combine_anagrams(words)
  word_classes = Hash.new()
  words.each do |w|
    # convert the word to an array of chars, sort the array, and convert it back to a word
    # shameless plug from programming ruby, 1.9
    # use downcase for key, since we want to put A and a into the same category
    key = w.downcase.unpack("C*").sort.pack("C*")
    (word_classes[key] ||= []) << w 
  end
  #puts word_classes
  # sort by key; this gives us 2-element arrays [key, value], and return the value
  return word_classes.sort {|key1,key2| key1 <=> key2}.map {|v| v[1]}
end  
