# SaaS Homework 3
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def anagrams?(w1, w2)
  w1.downcase.split('').sort == w2.downcase.split('').sort
#  puts "w1: #{w1.downcase.split('').sort}         w2: #{w2.downcase.split('').sort}"
end

def combine_anagrams(words)
  ret=[]
  while words.length>0 do
    i=0
    found=[words[i]]
#    puts "\nlooking for anagrams of #{found}"
    j=i+1
    while j<words.length do
      if anagrams?(words[i],words[j])
        found << words[j]
        words.delete_at(j)
      else
        j=j+1
      end
    end
    ret<< found
    words.delete_at(i)
  end
  ret
end

p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'Bill' ''])




#NOT USED 

# def combine_anagrams(words)
#   hash_words = Hash.new(0)
#   hash_values = Hash.new(0)
#   anagrams = Array.new(0)
# 
# # build hash of words as key and sorted letters of the word as value  
#   words.each { |x| hash_words[x] = x.to_s.downcase.split(//).sort }
#   
# 
#   puts "\n\n\n\n## hash of word, array string---------------------------------------------------------------"    
#   puts hash_words 
#   
#   puts "\n\n\n\n## show all same values---------------------------------------------------------------"    
# # iterate over words looking in has_words for corresponding values
# 
#    words.each do |k| 
#      puts "k is #{k}"
#      hash_words.each do |key,value|
#        puts "#{hash_words.values_at (k)} , #{hash_words[key]}"
#        if hash_words.values_at (k) == hash_words[key] then        
#          anagrams.push(key)
#          puts "match found on #{key}"
#        end 
#      end
#     end
#     puts "---------------------------------------------------------------" 
#     p anagrams   
#     puts hash_words 
# 
#     
# end
# 
# combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'Bill'])
