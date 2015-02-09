#!/usr/bin/env ruby

def combine_anagrams(words)
  groups = Hash.new { |h,k| h[k] = Array.new }
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    groups[sorted].push(word)
  end
  groups.values
end

#require 'test/unit'
#require 'set'
#
#class TestAnagrams < Test::Unit::TestCase
#
#  def test_combine
#    assert_equal(
#      [ ["cars", "racs", "scar"],
#        ["four"],
#        ["for"],
#        ["potatoes"],
#        ["creams", "scream"] ].to_set,
#      combine_anagrams(
#        ['cars', 'for', 'potatoes', 'racs',
#         'four','scar', 'creams', 'scream']).to_set)
#  end
#
#end
