require "test/unit"

class TestCombineAnagrams < Test::Unit::TestCase

	def test_simple_combine_anagrams
		argument = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
		result = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
		assert_equal(result.sort, combine_anagrams(argument).sort)
	end
	
end

def combine_anagrams(words)
	groups = Hash.new { default = [] }
	words.each do |word|
		key = word.downcase.chars.sort.join
		groups[key] = groups[key].push(word)
	end
	groups.values
end
