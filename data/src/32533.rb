def combine_anagrams(words)
  @res = Array.new
  @hash = Hash.new("Empty")
  @hash_rep = Hash.new("Empty")
  words.each do |x|
    @key = x.downcase.chars.sort.join
    if (@hash[@key] == "Empty") then
      @hash.store(@key, [x])
    else
      @hash[@key] = @hash[@key].concat([x])
    end
  end
  @res
  @hash.each_value do |x|
    (@res == nil) ? (@res = [x]) : (@res = @res.concat([x]))
  end
  print(@res)
  puts
end