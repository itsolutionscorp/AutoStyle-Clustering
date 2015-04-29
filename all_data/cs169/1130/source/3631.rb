def combine_anagrams(list)
  words = list.clone
  if words.size == 1 then
    return Array.new << words
  elsif words.size == 0 then
    return []
  end
  w1 = words[0]
  words.delete_at 0
  sub = Array.new
  sub << w1
  words.each do |w2|
      if w1.downcase.split(//).sort == w2.downcase.split(//).sort then
        sub << w2
      end
  end
  words.reject! { |x| sub.include? x }
  return combine_anagrams(words) << sub
end
