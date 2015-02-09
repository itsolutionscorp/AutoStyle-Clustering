# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
class String
  include Enumerable
  def each(&block)
    self.each_char( &block)
  end
end


def combine_anagrams(words)
  #Get the unique combo of anagrams for each word
  ((words.map do |word1|
    words.select do |word2|
      word1.downcase.sort == word2.downcase.sort
    end
  end ).reject {|wordset| wordset == []}).
  uniq
end

###TEST####
# result = combine_anagrams ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# expected = [["Cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# puts "RESULT: "
# p result
# 
# if result.sort == expected.sort
  # puts "PASSED TEST"
# else
  # puts "TEST FAILED\n Expected "
  # p expected
# end