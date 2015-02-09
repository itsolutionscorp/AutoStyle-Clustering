def combine_anagrams(words)
  dict = Hash.new([])
  words.each do |word|
    sorted_word = word.upcase.split('').sort.join
    dict[sorted_word] = [] unless dict.include?(sorted_word)
    dict[sorted_word] << word
  end
  return dict.values
end 

# class Array
  # alias_method :eq_original, :==
  # def ==(other)
    # self.sort.send(:eq_original, other.sort)
  # end
# end

def test_method(method_name, params, expected)
  puts "#{method_name}(#{params})"
  result = self.send(method_name, params)
  ok = result == expected
  puts " (%s) => #{result}" % (ok ? "OK" : "FAIL")
  puts " Expected: #{expected}" unless ok
  return ok
end

if __FILE__ == $0
  test_method(:combine_anagrams, ['HeLLo', 'hello'], [['HeLLo', 'hello']])
  test_method(:combine_anagrams, ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'],
    [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]])
end
