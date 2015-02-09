def combine_anagrams(words)
  h = Array.new
  w2 = Array.new
  words.each { |x| (w2 << x.downcase.split(//).sort.join) }
  words.each_index do |x|
    if (not words[x].empty?) then
      s = Array.new
      words.each_index do |y|
        if (w2[x] == w2[y]) then
          (s << words[y])
          words[y] = ""
        end
      end
      (h << s)
    end
  end
  return h
end

