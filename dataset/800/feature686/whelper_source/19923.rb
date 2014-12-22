def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    if h.has_key?(word.downcase.chars.sort.join) then
      (h[word.downcase.chars.sort.join] << word)
    else
      h[word.downcase.chars.sort.join] = [].push(word)
    end
  end
  a = Array.new
  h.each { |index, group| a.push(group) }
  return a
end

