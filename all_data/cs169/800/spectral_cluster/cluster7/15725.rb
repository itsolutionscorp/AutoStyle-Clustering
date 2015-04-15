# Merges two sorted lists into one sorted list.
def merge(left, right)
  #puts "Merging #{left} #{right}"
  merged = ""
   l_idx = 0
  r_idx = 0
  ctr = 0

  # Set ctr_max to the smaller length between left and right's lengths.
  ctr_max = (right.length > left.length) ? right.length : left.length
  #puts ctr_max
  while (ctr <= ctr_max)
    #puts "Comparing #{left[l_idx]} #{right[r_idx]}"
   
    if (left[l_idx] and right[r_idx] and left[l_idx] <= right[r_idx])
      merged << left[l_idx]
      l_idx += 1
    elsif (right[r_idx])
      merged << right[r_idx]
      #puts merged
      r_idx += 1
    end
    ctr += 1
  end

  #puts "#{l_idx} #{r_idx}"

  if (l_idx < left.length)
   # puts "left is not done"
    for i in (l_idx .. left.length - 1)
      merged << left[i]
      #puts merged
    end
  end

  if (r_idx < right.length)
    for i in (r_idx .. right.length - 1)
      merged << right[i]
    end
  end

  #puts "merged result: #{merged}"
  return merged
end

# Sorts a string or list using merge sort algorithm.
# Sorted results are in ascending order.
def merge_sort(str)
  #puts "merge sorting #{str}"

  if (str.length <= 1 )
    #puts "Return #{str}"
    return str
  end
  
  mid = str.length / 2
  left = str[0..mid-1]
  right = str[mid, str.length - 1]
  puts "left: #{left} right: #{right}"

  ret= merge(merge_sort(left), merge_sort(right))
  #puts "Got #{ret}"
  return ret
end

# Converts a hash to an array, where eache element in the array
# is a subarray containing a key and its values.
def hash_to_array(h)
  arr = Array.new
  for k in h.keys
    lst = [k]
    lst << h[k]
    arr << lst.flatten
  end
  return arr
end

# Give a list of words, returns a grouping of anagrams.
# The format of the return value is an array of subarrays. Each subarray
# contains words that share the same anagram.
def combine_anagrams(words)
  anagrams = Hash.new([])
  for i in 0..(words.length - 1)
    anagram_found = false
    if (i == 0)
      anagrams[words[i]] = []
    end
    for j in 0..(i-1)
#      puts "Comparing #{words[i]} and #{words[j]}"
      anagram1 = merge_sort(words[i].downcase)
      anagram2 = merge_sort(words[j].downcase)
#      puts "#{anagram1} #{anagram2}"
      if (merge_sort(words[i].downcase) == merge_sort(words[j].downcase))
#        puts "#{words[i]} and #{words[j]} are the same anagram."
        anagrams[words[j]] << words[i]
        anagram_found = true
        #puts "anagrams: #{anagrams.to_s}"
        break
      end
    end
    anagrams[words[i]] = [] unless (anagram_found)
  end

  # Convert hash to an array.
  anagrams_arr = hash_to_array(anagrams)
  return anagrams_arr
  #return anagrams
end
