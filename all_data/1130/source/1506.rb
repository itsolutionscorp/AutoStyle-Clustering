# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def are_anagrams?(w1, w2)
  return w1.downcase.chars.to_a.sort == w2.downcase.chars.to_a.sort
end

def clean_array(arr, tmpl_idx)
  tmpl = arr[tmpl_idx]
  #puts "tmpl_idx=" + tmpl_idx.to_s + " tmpl=" + tmpl
  (arr.length-1).downto(0) do |i|
    #puts "i=" + i.to_s + " arr[i]=" + arr[i] + " tmpl=" + tmpl
    if not are_anagrams?(arr[i], tmpl)
      #puts "del"
      arr.delete_at(i)
    end
  end
end

def combine_anagrams(words)
  #puts are_anagrams?(words[0],words[1])
  result = Array.new
  
  words.length.times do
    result << Array.new(words)
  end
  
  #puts result
  #puts "@@@@@@@@@@@@@@"
  
  for i in 0..(result.length-1) do
    clean_array(result[i], i)
  end
  
  
  return result.uniq
end
