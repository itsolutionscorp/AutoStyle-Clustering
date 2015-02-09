def combine_anagrams(words)
  h = {}
  words.each do |w|
    word = w.gsub(/\W/,'').downcase.split('').sort.join
    h[word] = [] unless h[word]
    h[word] << w
  end
  puts h.inspect
  h.values
end
