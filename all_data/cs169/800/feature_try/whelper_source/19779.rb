def combine_anagrams(words)
  words.inject(Hash.new { |h, k| h[k] = [] }) do |h, i|
    (h[i.chars.sort_by(&:downcase).join] << i)
    h
  end.values
end

