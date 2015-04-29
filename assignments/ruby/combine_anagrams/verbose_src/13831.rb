def combine_anagrams(words)
  
  temp_hash=Hash.new([])
  
  words.each do |a_word|
    sorted_word=a_word.downcase.split(/(.{1})/).sort.join
    if temp_hash.has_key?(sorted_word)
      temp_hash[sorted_word]<<a_word
      #puts("Appending word #{a_word} to key #{sorted_word} to make #{temp_hash[sorted_word]}")
    else
      temp_hash[sorted_word]=[a_word]
      #puts("Creating new key of word #{sorted_word} and adding first word word #{a_word} to make an array: #{temp_hash[sorted_word]}")
      
    end
    
    #p sorted_word
    #p temp_hash[sorted_word]
    #p temp_hash
    
  end
  
  p temp_hash
  
  return_array=Array.new()
  
  temp_hash.each {|key, value| return_array<<value }
  
  return_array
  
end