def combine_anagrams(words)
  dup_words = Array.new(words)
  index = 0
  len = dup_words.length
  dup_words.each do |t|
    dup_words.push(((t.downcase.split(//).sort.join + "_") + index.to_s))
    index = (index + 1)
    break if (index == len)
  end
  dup_words.slice!(0, (dup_words.length / 2))
  dup_words.sort!
  last_elem = nil
  ans = Array.new
  sub_ans = Array.new
  dup_words.each do |t|
    /([a-z]+)_(\d+)/ =~ t
    if (last_elem == $1) then
      sub_ans.push(words[$2.to_i])
    else
      ans.push(sub_ans) if (last_elem != nil)
      sub_ans = Array.new
      sub_ans.push(words[$2.to_i])
      last_elem = $1
    end
  end
  ans.push(sub_ans) if (last_elem != nil)
  return ans
end