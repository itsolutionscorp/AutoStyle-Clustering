def combine_anagrams(words)
  h = Hash[]
  words.each do |i|
    if h[i.downcase.chars.sort.join].nil? then
      h[i.downcase.chars.sort.join] = [i]
    else
      h[i.downcase.chars.sort.join].push(i)
    end
  end
  h.to_a.map { |i| i[1] }
end

