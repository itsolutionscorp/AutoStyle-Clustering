


def combine_anagrams(words)
  words.group_by{ |w|    w.downcase.chars.sort.join  }.values
  
end


require "test/unit"

class PartThreeTest   < Test::Unit::TestCase
  
  def test_anagrams
   res =   combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
   
   assert_equal [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]].sort , res.sort
   
   res2 = combine_anagrams [ 'a', 'A'].sort
   
   assert_equal res2, [['a', 'A']].sort
  end
  
end
