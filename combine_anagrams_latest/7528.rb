def combine_anagrams(words)
  h = Hash.new
  result = []
  words.each {|word| h[word.downcase.chars.sort()] = [word] + h.fetch(word.downcase.chars.sort(),[])  }
  result = h.map { |k,arr| arr }
  return result
end
