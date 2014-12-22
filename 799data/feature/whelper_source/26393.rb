def combine_anagrams(words)
  outp = Hash.new
  words.each do |w|
    idx = w.downcase.chars.to_a.sort.join
    outp[idx] = Array.new if (outp[idx] == nil)
    outp[idx].push(w)
  end
  return outp.values
end

