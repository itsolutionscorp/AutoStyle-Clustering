def combine_anagrams(words)
  groups=Hash.new()
  words.each { |w|  
    chars=[]
    w.each_char { |c|
      chars << c      
    }
    key = chars.sort.join
    if groups[key]
      groups[key] << w
    else
      groups[key] = [w]
    end
  }
 groups.collect { |k,v| v }
end

__END__


require "test/unit"

class TestCombineAnagrams < Test::Unit::TestCase
  def test_combine_anagrams
    input = %w[ cars fors potatoes racs four scar creams scream]
    output = combine_anagrams(input)
    assert_equal([
      ["four"],
      ["fors"],
      ["cars", "racs", "scar"],
      ["creams", "scream"],
      ["potatoes"]
      ], output)
    end
  end