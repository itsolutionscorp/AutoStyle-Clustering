def merge_sort(str)
  return str if (str.length <= 1)
  mid = (str.length / 2)
  left = str[(0..(mid - 1))]
  right = str[mid, (str.length - 1)]
  puts("left: #{left} right: #{right}")
  ret = merge(merge_sort(left), merge_sort(right))
  return ret
end

def hash_to_array(h)
  arr = Array.new
  for k in h.keys do
    (lst = [k]
    (lst << h[k])
    (arr << lst.flatten))
  end
  return arr
end

def combine_anagrams(words)
  anagrams = Hash.new([])
  for i in (0..(words.length - 1)) do
    (anagram_found = false
    anagrams[words[i]] = [] if (i == 0)
    for j in (0..(i - 1)) do
      (anagram1 = merge_sort(words[i].downcase)
      anagram2 = merge_sort(words[j].downcase)
      if (merge_sort(words[i].downcase) == merge_sort(words[j].downcase)) then
        (anagrams[words[j]] << words[i])
        anagram_found = true
        break
      end)
    end
    anagrams[words[i]] = [] unless anagram_found)
  end
  anagrams_arr = hash_to_array(anagrams)
  return anagrams_arr
end

