def combine_anagrams(words)
  out_array = Array.new(0)
  words.each do |w1|
    temp_array = []
    words.each do |w2|
      if (w2.downcase.split(//).sort == w1.downcase.split(//).sort) then
        temp_array.push(w2)
      end
    end
    out_array.push(temp_array)
  end
  p(out_array.uniq)
end