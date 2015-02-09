def combine_anagrams(words)
  final = Array.new
  n = 0
  words.each do |w|
    temp = Array.new
    temp = w.downcase.split(//).sort
    temp.sort
    final[n] = temp
    n = (n + 1)
  end
  final.uniq!
  really_final = Array.new
  final.each do |x|
    semi_final = Array.new
    words.each do |y|
      temp = y.downcase.split(//).sort
      semi_final.insert(semi_final.length, y) if (temp == x)
    end
    really_final.insert(really_final.length, semi_final)
  end
  really_final.sort
  return really_final
end

