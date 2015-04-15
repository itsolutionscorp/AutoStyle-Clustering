def combine_anagrams(words)
  if words.length == 0
    return []
  end
  sorted = []
  indexed = []
  words.each { |w| sorted << w.downcase.chars.sort.join }
  sorted.each_index { |i| indexed << [sorted[i], i] }
  indexed.sort! {|x,y| x[0] <=> y[0]}
  ret = []
  curset = []
  cur = ''
  indexed.each do |x|
    if x[0] != cur
      if cur != ''
        ret << curset
      end
      curset = [words[x[1]]]
      cur = x[0]
    else
      curset << words[x[1]]
    end
  end
  ret << curset

  return ret
end
