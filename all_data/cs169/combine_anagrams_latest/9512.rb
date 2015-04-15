### Part III - anagrams

def combine_anagrams(words)
  h = Hash.new()
  
  words.each do |w|
    l = w.downcase.split('').sort.join
    if h.has_key?(l)
      h[l] << w
    else
      h[l] = [w]
    end
  end
  h.values
end
