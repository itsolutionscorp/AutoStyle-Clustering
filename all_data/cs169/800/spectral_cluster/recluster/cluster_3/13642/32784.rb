require "pry"

def match(word)
  word.split(//).sort.join
end

def combine_anagrams(words)
  groups = []
  words.each do |i|
    i_array = []
    words.each do |j|
      if match(i) == match(j)
        i_array << j
      end
    end
    groups << i_array
  end
  groups.map! do |group|
    group.sort
  end
  groups.uniq!
  groups
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s

#require 'test/unit'
##class TestAnagram < Test::Unit::TestCase 
#  def book_test
#    assert_equal([["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]], combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])) 
#  end
 # def book_test_fail
#    assert_equal([["carDs", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]])combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']), 
#  end

#book_test
