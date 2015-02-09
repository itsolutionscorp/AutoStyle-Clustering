# mycode.rb  


# def combine_anagrams(words)

# input_keys = Array.new()
# input_keys = eval (words[0])



# my_hash = Hash.new()


# words.each do {|key|}

# my_hash = input_keys.each_with_object(Hash.new []) do |word, hash|
  # hash[word.chars.sort] += [word]
# end

# output_array = Array.new()

# my_hash.each_value { |hash_value| output_array.push(hash_value)}


# output_array

# end




def combine_anagrams(words)
  
  anagram_hash = Hash.new(0)
  anagram_array = Array.new()
  
  words.each do |x|
    
    sorted = x.downcase.split(//).sort.to_s
    
    if anagram_hash.has_key?(sorted)
      anagram_hash[sorted].push(x)
    else
       anagram_hash[sorted] = Array.new().push(x)
    end
    
  end
  
  anagram_hash.each do |key, value|
    anagram_array.push(value)
  end
  
  return anagram_array
  
end


p combine_anagrams( ["cars", "for", "potatoes", "racs", "four","scar", "creams", "scream"])