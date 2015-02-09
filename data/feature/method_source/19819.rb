def combine_anagrams(words)
  orderedInput = []
  output = []
  words.each { |word| orderedInput.push(word.downcase.chars.sort.join) }
  orderedInput.each_with_index do |blurb, i|
    if (blurb != nil) then
      tmp_a = []
      tmp_a.push(words[i])
      orderedInput[i] = nil
      orderedInput.each_with_index do |needle, j|
        if (blurb == needle) then
          tmp_a.push(words[j])
          orderedInput[j] = nil
        end
      end
      output.push(tmp_a)
    end
  end
  output
end