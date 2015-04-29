def stringer(x)
  temp_word = ""
  x.each { |y| (temp_word << y) }
  return temp_word
end

def combine_anagrams(words)
  return [] if words.empty?
  copy = words
  copy = copy.map do |x|
    bank = []
    x.downcase.each_char { |c| (bank << c) }
    bank.sort
  end.uniq
  unique_dict = []
  copy.each do |x|
    temp_word = stringer(x)
    (unique_dict << temp_word)
  end
  to_return = []
  unique_dict.each do |unique_word|
    (to_return << words.select do |original_word|
      bank = []
      original_word.downcase.each_char { |c| (bank << c) }
      bank.sort!
      to_compare = stringer(bank)
      (unique_word == to_compare)
    end)
  end
  to_return.to_s
end

