def combine_anagrams(words)
  return words if (words == [])
  if words[0].kind_of?(String) then
    anagrams = words.group_by { |word| word.downcase.chars.sort }.values
  else
    if words[0].kind_of?(Array) then
      anagrams = words.group_by { |word| word.sort }.values
    else
      raise(IncorrectInputError)
    end
  end
  return anagrams
end