#require "pry"
#require "test/unit"

def combine_anagrams(words)
  a = []
  words.each do |i|
    b = []
    words.each do |j|
      if i.downcase.split('').sort == j.downcase.split('').sort
        b << j
      end
    end
    a << b
  end
  return a.uniq
end

#class RPSTest < Test::Unit::TestCase
#  def test_anagrams
#    assert_equal [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]].sort, combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).sort
#  end
#end
## input: 
## ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
## output:
## [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
