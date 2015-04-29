#!/usr/local/bin/ruby
#

def combine_anagrams(words)
  groups = Hash.new {|h,k| h[k] = []}
  words.each do |word|
    groups[word.downcase.chars.sort.join] << word 
  end

  result = []
  groups.each_value {|value| result << value}
  result
end
 