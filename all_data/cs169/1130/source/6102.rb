def combine_anagrams(words)
  res = []
  words.each do |w|
    gr = res.find { |g| g.first.downcase.chars.sort.join == w.downcase.chars.sort.join }
    if gr
      gr.push w
    else
      res.push [w]
    end
  end
  return res
end
