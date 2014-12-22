def is_anagram(word1, word2)
  array1 = word1.downcase.split(//)
  array1.sort!
  array2 = word2.downcase.split(//)
  array2.sort!
  return (array1 == array2)
end

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

