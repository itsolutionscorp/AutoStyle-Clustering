#['cars','for', 'potatoes', 'racs', 'four', 'scar', 'scream', 'creams']
def sort_s(s)
  arr = s.downcase.chars.to_a
  arr1 = arr.sort()
  s1 = ''
  arr1.each{|c| s1 += c}
  return s1
end

def combine_anagrams(words)
  swords = words.map do |ww|
    sort_s(ww)
  end
  result = Array.new(words.length){|i| nil}
  idx = 0
  for i in 0..words.length()-1
    if result[i] == nil
      idx = i
      result[i] = idx
      for j in i.. words.length()-1
        result[j] = idx if swords[j] == swords[i]
      end
    end
  end 
  anagrams = Array.new
  for i in 0..result.length()-1
    tmp = result[i]
    if anagrams[tmp] == nil
      anagrams[tmp] = Array.new
    end
    anagrams[tmp][anagrams[tmp].length] = words[i]  
  end
  anagrams.delete(nil)
  return anagrams
end
