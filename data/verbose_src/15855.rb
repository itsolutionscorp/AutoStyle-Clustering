def combine_anagrams(words)
  a = Hash.new # anagramm hash
  words.each do |word|
    done = false;
    a.each_key do |key|
      if (word.chars.sort.join.downcase == key)
        a[key] = a[key] + [word]
        done = true
      end
    end
    newkey = word.chars.sort.join.downcase
    a[newkey] = [word] if (done == false)
  end
  result = Array.new
  counter = 0
  a.each_value do |value|
    result[counter] = value
    counter += 1
  end
  return result
end

print combine_anagrams(['A','a','cars','for','potatoes','racs','four','scar','creams','scream'])
