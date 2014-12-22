def combine_anagrams(words)
# <YOUR CODE HERE>
  h = Hash.new
  words.each do |w|
    sorted = w.downcase.split('').sort.join
    if h.has_key?(sorted)
       h[sorted]= h[sorted].push(w)
    else
       h[sorted] = Array.new(1,w)
    end
  end
  return h.values
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four',
#  'scar', 'creams', 'scream']).to_s
