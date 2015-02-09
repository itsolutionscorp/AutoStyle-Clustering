def combine_anagrams(words)
  r = words.inject(Hash.new{|h,k| h[k] = []}) { |h, st|
    s = st.downcase.chars.sort { |a, b| a.casecmp(b) }.join
    h[s] << st
    h
  }
  r.values
end
