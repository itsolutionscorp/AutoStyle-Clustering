def combine_anagrams(words)
  table = Hash.new
  words.each do |word|
    key = word.downcase.chars.to_a.sort
    if table.has_key?(key) then
      table[key] = (table[key] + [word])
    else
      table[key] = [word]
    end
  end
  return table.values
end

