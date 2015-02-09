def combine_anagrams(words)
  # downcase and sort each word into an array, ("sorted & downcase","original")
  h = Hash.new{|h,k| h[k] = []}
    words.each do |w|
      k = string_to_char_arr(w.downcase)
      h[k] << w 
    end
  puts h
    
  # Put into groups that match
  word_groups = []
  h.each_value do |v|
    word_groups << v
  end
  return word_groups
end

def string_to_char_arr(word)
  letters = []
  word.each_char {|l| letters << l }
  letters.sort.join
end



puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'Scream']).inspect
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
puts combine_anagrams(["creams", "scream"]).inspect
