def combine_anagrams(words)
  h = Hash.new
  (0..words.length-1).each do | i |
    sorted_word = words[i].downcase.chars.sort.join
    if h.has_key?(sorted_word) == false
      h[sorted_word] = [i]
    else 
      h[sorted_word] += [i]
    end
  end
  
  anagrams = Array.new(h.length)
  j = 0
  h.each_pair do | key, value |
    an_arr = Array.new(value.length)
    (0..value.length-1).each do |i|
      an_arr[i] = words[value[i]]
    end
    anagrams[j] = an_arr
    j += 1
  end
  return anagrams
end
