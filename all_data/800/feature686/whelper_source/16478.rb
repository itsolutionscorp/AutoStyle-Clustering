def combine_anagrams(words)
  output = Array.new
  words.each do |a|
    (output << words.find_all do |b|
      (a.downcase.chars.to_a.sort == b.downcase.chars.to_a.sort)
    end)
  end
  return output.uniq
end

def combine_anagrams(words)
  output = Array.new
  words.each do |a|
    (output << words.find_all do |b|
      (a.downcase.chars.to_a.sort == b.downcase.chars.to_a.sort)
    end)
  end
  return output.uniq
end

