def word_sort(word)
  return word.downcase.split(//).sort.join
end

def combine_anagrams(words)
  output = Hash.new
  words.each_with_index do |w, index|
    w2 = word_sort(w)
    output.has_key?(w2) ? ((output[w2] << w)) : (output[w2] = [w])
  end
  return output.values
end

