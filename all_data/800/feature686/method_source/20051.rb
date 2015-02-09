def combine_anagrams(words)
  count = {}
  words.each do |w|
    count[w.downcase.chars.sort.join] ||= []
    (count[w.downcase.chars.sort.join] << w)
  end
  ret = []
  count.each_key { |c| (ret << count[c]) }
  return ret
end