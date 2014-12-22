def combine_anagrams(words)
  return [] if words.empty?
  test = words.collect { |x| x.downcase.split(//).sort.join }
  totalanagrams = Array.new
  test.each do |x|
    anagrams = Array.new
    test2 = Array.new(test)
    i = 0
    while (test2.index(x) != nil) do
      anagrams.push(words.fetch((test2.index(x) + i)))
      test2.delete_at(test2.index(x))
      puts(test2.inspect)
      i = (i + 1)
    end
    totalanagrams.push(anagrams).uniq!
  end
  return totalanagrams
end

