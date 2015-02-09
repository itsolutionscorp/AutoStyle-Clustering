def combine_anagrams(words)
  new_words = Array.new
  result = Array.new
  words.each { |w| (new_words << w.downcase.chars.sort.join) }
  a = new_words.uniq
  a.each do |k|
    b = new_words.map { |it| (k == it) }
    res = Array.new
    b.length.times { |m| (res << words[m]) if (b[m] == true) }
    (result << res)
  end
  return result
end

