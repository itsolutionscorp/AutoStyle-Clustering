def combine_anagrams(words)
  found = false
  groups = Array.new
  words.each do |word|
    if groups.empty? then
      groups.push(Array.new(1, word))
    else
      found = false
      groups.each do |group|
        if is_anagram(group.at(0), word) then
          group.push(word)
          found = true
          break
        end
      end
      groups.push(Array.new(1, word)) if (found == false)
    end
  end
  return groups
end