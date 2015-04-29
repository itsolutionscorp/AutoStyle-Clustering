def combine_anagrams(words)
  reg_h = {}
  words.each do |word|
    reg = word.downcase.chars.sort.join
    reg_h.key?(reg) ? (reg_h[reg].push(word)) : (reg_h[reg] = [word])
  end
  reg_h.each_value.to_a
end

