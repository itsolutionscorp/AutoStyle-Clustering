def combine_anagrams(words)
  act = Array.new
  words.each_with_index { |val, i| act[i] = val.upcase.split("").sort }
  out = Array.new
  len = (act.length - 1)
  count = 0
  act.each_with_index do |val, i|
    next if act[i].nil?
    out[count] = Array.new(1, words[i])
    ((i + 1)..len).each do |c|
      if (act[i] == act[c]) then
        (out[count] << words[c])
        act[c] = nil
      end
    end
    count = (count + 1)
  end
  out
end