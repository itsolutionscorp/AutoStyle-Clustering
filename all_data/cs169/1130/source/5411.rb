def are_anagrams?(s1, s2)
  if s1.downcase.chars.sort.join == s2.downcase.chars.sort.join
    return true
  else
    return false
  end
end

def combine_anagrams(words)
  sorted = []
  words.each do |w1|
    group = [w1]
    words[words.index(w1)] = ""
    if words.size > 1
      words.each do |w2|
        if are_anagrams?(w1, w2)
          group.push(w2)
          words[words.index(w2)] = ""
        end
      end
    end
    if group[0] =~ /\w+/
      sorted.push(group)
    end
  end
  return sorted
end
