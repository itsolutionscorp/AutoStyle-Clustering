def combine_anagrams(words)
  unique = Array.new
  anagrams = Array.new
  words.each do |a|
    if unique.include?(a.downcase.chars.sort.join) then
      (anagrams[unique.index(a.downcase.chars.sort.join)] << a)
    else
      (unique << a.downcase.chars.sort.join)
      (anagrams << [a])
    end
  end
  return anagrams
end

