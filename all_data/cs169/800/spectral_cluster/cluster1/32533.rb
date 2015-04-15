def combine_anagrams(words)
@res = Array.new
@hash = Hash.new("Empty")
@hash_rep = Hash.new("Empty")
words.each do |x|
@key = x.downcase.chars.sort.join

if @hash[@key] == "Empty"
@hash.store(@key, [x])
else
@hash[@key] =  @hash[@key].concat([x])
end
end

@res 

@hash.each_value do |x|
if(@res == nil)
@res = [x]
else
@res = @res.concat([x])
end
end
print @res
puts
end

combine_anagrams([])
combine_anagrams(['c', 'c', 'b'])
combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
