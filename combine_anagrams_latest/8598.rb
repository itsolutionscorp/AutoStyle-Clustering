class String
  def is_anagram(string)
    a = self.downcase.split(//).sort.join
    b = string.downcase.split(//).sort.join
    a == b
  end
end

def combine_anagrams(words)
  p = words
  r =[]
  while p.length>0
    p1, p = p.partition{|s| p[0].is_anagram(s)}
    r.push(p1)
  end
  r
end
