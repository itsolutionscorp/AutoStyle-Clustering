def are_anagrams?(a, b)
  return a.downcase.chars.sort.join == b.downcase.chars.sort.join
end

def combine_anagrams(words)
  return Array.new if words.length == 0
  result = [[words[0]]]
  words.each_index do |i|
    if i > 0
      is_new = true
      result.each do |r|
        if are_anagrams?(r[0], words[i])
          r.push(words[i])
          is_new = false
          break
        end
      end
      if is_new
          n = [words[i]]
          result.push(n)
      end
    end
  end
  return result
end