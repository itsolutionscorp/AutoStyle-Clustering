def combine_anagrams(words)
  lets_sorted = []
  wordy_st = ""
  hashy = {}
  anagrams = []
  first_case = true
  words.each do |x|
    wordy_st = x.downcase.chars.sort.join
    (lets_sorted << wordy_st)
    wordy_st = ""
  end
  lets_sorted.each_index do |a|
    key_a = lets_sorted[a]
    if (not hashy.has_key?(key_a)) then
      hashy[key_a] = [words[a]]
    else
      hashy[key_a] = (hashy[key_a] << words[a])
    end
  end
  hashy.each { |key, value| (anagrams << value) }
  anagrams
end