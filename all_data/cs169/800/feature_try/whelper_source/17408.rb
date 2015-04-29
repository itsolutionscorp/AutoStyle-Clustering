def combine_anagrams(words)
  words.sort! { |a, b| (a.length <=> b.length) }
  letters = words.map { |w| w.downcase.split(//).sort }
  outp = Array.new
  prev_a = []
  for w in words do
    if (letters[words.index(w)] == prev_a) then
      outp[(outp.length - 1)].push(w)
    else
      outp.push([w])
      prev_a = letters[words.index(w)]
    end
  end
  return outp
end

