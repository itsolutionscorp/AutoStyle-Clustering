def is_anagrams(s1, s2)
  return s1.downcase.chars.sort.join == s2.downcase.chars.sort.join
end

def combine_anagrams(words)
  result = Array.new
  words.sort.each do |w|
    sort_it=false
    result.map! do |g|
      if is_anagrams(g[0], w)
        sort_it=true
        g = g + [w]
      end
      g
    end
    if !sort_it
      result += [[w]]
    end
  end
  result
end