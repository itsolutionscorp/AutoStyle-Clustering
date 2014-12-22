def to_canonicForm(string)
  string = string.downcase
  spl = string.scan(/./)
  spl.sort!
  return spl
end

def combine_anagrams(words)  
  d = {}
  words.each { |word|    
    canonicForm = to_canonicForm(word)
    if d.has_key?(canonicForm)
      d[canonicForm] += [word]
    else
      d[canonicForm] = [word]
    end
  }
  return d.values
end