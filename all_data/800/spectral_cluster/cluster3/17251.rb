def combine_anagrams(words)
  #   <YOUR CODE HERE>
  h = {}
  words.each do |w|
    #http://stackoverflow.com/a/9464133
    key = w.downcase.chars.sort { |a, b| a.casecmp(b) } .join
    if h.has_key?(key)
      h[key] << w
    else
      h[key] = [w]
    end
  end
  a = []
  h.each do |key, val|
    a << val
  end
  a
end

# input:
 words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

# p combine_anagrams(words)