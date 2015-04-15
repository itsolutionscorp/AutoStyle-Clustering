def combine_anagrams(words)
  temp = words.collect { |x| x.downcase.chars.sort.to_s }
  temp.uniq!
  final = temp.collect do |alph|
    temp2 = []
    words.each { |x| (temp2 << x) if (x.downcase.chars.sort.to_s == alph) }
    temp2
  end
  final
end

