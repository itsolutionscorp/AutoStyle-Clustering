
def combine_anagrams(words)
  anagrams = Hash.new {|h,k| h[k] = []}
  words.each do |word| #for each word in the list
    sorted_word_key = word.downcase.split(//).sort.map { |i| i.to_s }.join("") #create a sorted array of lowercase chars we can use as a Hash key
    #print sorted_word_key, "\n"
    anagrams[sorted_word_key] << word #append the word to the array within the hash
  end
  final_list = Array.new
  anagrams.each do |el|
    final_list << el.last
  end
  final_list
end

#x = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#print combine_anagrams(x)
