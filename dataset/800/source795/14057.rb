# Part 3

def combine_anagrams(words)
  
  h = Hash.new
  words.each do |w|
    w_sorted = w.downcase.chars.sort.join
    if h.has_key?(w_sorted)
      h[w_sorted] << w
    else
      h[w_sorted] = [w]
    end
  end
  
  array = []
  
  h.each_key do |key|
    array << h[key]
  end
  
  array
  
end