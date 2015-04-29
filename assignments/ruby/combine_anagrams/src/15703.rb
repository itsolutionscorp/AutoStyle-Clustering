def combine_anagrams(words)
  anagram_group = Hash.new([])
  final_array = Array.new
  words.each do |word|
    anagram_group[word.chars.sort_by(&:downcase).join] += [word]
  end
  anagram_group.each_value { |v| final_array.push(v) }
  return final_array
end