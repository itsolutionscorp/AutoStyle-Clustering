def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    anagram_form = word.downcase.chars.sort.join
    ((groups[anagram_form] ||= []) << word)
  end
  groups.values
end

