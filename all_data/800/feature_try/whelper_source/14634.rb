def combine_anagrams(words)
  groups = Hash.new { |h, k| h[k] = [] }
  words.inject(groups) do |h, k|
    (h[k.upcase.chars.sort.join] << k)
    h
  end.values
end

