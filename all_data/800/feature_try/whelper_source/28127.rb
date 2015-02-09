def combine_anagrams(input)
  return anagram_helper(input, Array.new)
end

def anagram_helper(input, groups)
  if (input.size == 0) then
    return groups
  else
    (groups << input.select { |a| (sort_word(a).casecmp(sort_word(input.at(0))) == 0) })
    reducedInput = input.reject { |b| (sort_word(b).casecmp(sort_word(input.at(0))) == 0) }
    return anagram_helper(reducedInput, groups)
  end
end

