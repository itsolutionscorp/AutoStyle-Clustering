def combine_anagrams(words)
  result = []
  until words.empty? do
    (current = words.pop
    temp = []
    add = [current]
    until words.empty? do
      (nextw = words.pop
      current.is_anagram(nextw) ? (add.push(nextw)) : (temp.push(nextw)))
    end
    result.push(add)
    words = temp)
  end
  return result
end