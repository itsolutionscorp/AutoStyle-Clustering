# Part 3: anagrams

def combine_anagrams(words)
  return [] unless words && ! words.empty?
  groups = words.inject({}) do |h, word|
    s = word.downcase.split('').sort
    h.has_key?(s) ? h[s] << word : h[s] = [word]
    h
  end
  groups.map {|ignore, group| group}
end
 
