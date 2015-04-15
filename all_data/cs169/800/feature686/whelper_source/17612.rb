def combine_anagrams(words)
  groups = Hash.new { |h, k| h[k] = Array.new }
  words.each do |w|
    key = w.downcase.chars.sort.join
    groups[key].push(w)
  end
  groups.values
end

