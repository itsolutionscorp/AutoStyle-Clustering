# Homework 1
# Part 3
# Anagrams

def combine_anagrams(words)
 hash = {}
 result = []
 words.each{ |x|
  if hash.has_key?((x.downcase.scan(/\w/)).sort)
   hash.store((x.downcase.scan(/\w/)).sort,hash.fetch((x.downcase.scan(/\w/)).sort).push(x))
  else
   hash.store((x.downcase.scan(/\w/)).sort,[x])
  end
 }
             hash.each_value {|value| result.push(value)}
 return result
end

puts
print combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'] )
