# 3

def combine_anagrams(words)
  anags = {}
  words.each do |w|
    key = w.downcase.split(/\s*/).sort.to_s.to_sym
    if anags.member? key
      anags[key].push(w)
    else
      anags[key] = [w]
    end
  end
  return anags.values
end
