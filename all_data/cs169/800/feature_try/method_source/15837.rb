def combine_anagrams(words)
  out = []
  for word in words do
    (in_output = false
    for o in out do
      if is_anagram(o[0], word) then
        o.push(word)
        in_output = true
      end
    end
    out.push([word]) if (not in_output))
  end
  return out
end