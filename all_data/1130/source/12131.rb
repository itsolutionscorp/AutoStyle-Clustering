def combine_anagrams(words)
  sorted_uniq = words.clone
  sorted_uniq.to_a.map! {|word| word.to_s.downcase.chars.sort.to_a.join}.uniq!
  result = Array.new
  sorted_uniq.each_index {|index| result[index] = Array.new}
  words.to_a.each do |word|
    result[sorted_uniq.find_index(word.to_s.downcase.chars.sort.to_a.join)].to_a.push(word)
  end
  result
end