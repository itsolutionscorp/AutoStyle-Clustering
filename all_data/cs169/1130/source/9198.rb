def combine_anagrams(words)
  h = Hash.new
  words.each{|element| h[sorted(element)]==nil ? h[sorted(element)] = [element] :
      h[sorted(element)].push(element)}
  h.values
end

def sorted(word)
  word.downcase.chars.sort.join
end