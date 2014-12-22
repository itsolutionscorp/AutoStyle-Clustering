def combine_anagrams(words)
  words = words.map { |w| [w, w.downcase.scan(/./).sort.join] }
  ag = []
  while (words.length > 0) do
    bs = words.shift
    matches = words.select { |e| (e[1] == bs[1]) }.map { |e| e[0] }.unshift(bs[0])
    ag.push(matches)
    words = words.delete_if { |e| (e[1] == bs[1]) }
  end
  print((ag.to_s + "\n|"))
  return ag
end