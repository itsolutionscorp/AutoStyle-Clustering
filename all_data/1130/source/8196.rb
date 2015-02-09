def combine_anagrams(words)
  
  grouped = Array.new

  new_words_hash = Hash.new
  words.each do |w|
    new_word = w.downcase.split(//).sort  #split in character and then sort them
    #puts new_word.inspect
        
    if (new_words_hash.has_key?(new_word))
        #puts "AFTER"
        temp = new_words_hash[new_word] 
        new_words_hash[new_word] = temp.push(w)
        #puts "new_words_hash[new_word] = #{new_words_hash[new_word].inspect}" 
    else
        #puts "FIRST TIME"
        new_words_hash[new_word] = [w]
        #puts "new_words_hash[new_word] = #{new_words_hash[new_word].inspect}" 

    end
  end      
  
  new_words_hash.each_value { |value| grouped.push(value)}

  puts grouped.inspect
  return grouped 
 end

combine_anagrams(["CaRS", "for", "potatoes", "racs", "four", "scaR", "creams", "scream"])

