class Scrabble 
	@@points = Hash.new(0)

	[
		%w{ a e i o u l n r s t }, # 1   point 
		%w{ d g },                 # 2   points   
		%w{ b c m p },             # 3   points 
		%w{ f h v w y},            # 4   points
		%w{ k },                   # 5   points
		[],[],                     # 6,7 points
		%w{ j x },                 # 8   points
		[],                        # p   points
		%w{ q z }                  # 10  points
	].each_with_index do |list, index| 
		list.each {|letter| @@points[letter] = index + 1}
	end

	def initialize(word, options = {})
		# wish this could look better
		@word = word || ""
		@word.gsub(/\s/,'')
		@word.downcase!

		@options = Hash.new([]).merge!(options)
	end

	# so ugly...
	def score
	  @score || begin	
		  word = @word
			# adds extra letters for double and tripple letters
			@options[:double_letter].each {|letter| word << letter}	
			@options[:tripple_letter].each {|letter| word << letter * 2}

			# gets the score for the word including the double/tripple letters
			@score = @word.each_char.inject(0) do |sum, char| 
				sum += @@points[char]
			end	

			# multiplies the word for double and tripple word scores
			# the to_s.to_i is there to convert [] => 0
			@score *= (2 ** @options[:double_word].to_s.to_i)
			@score *= (3 ** @options[:tripple_word].to_s.to_i)
		end
	end

	def self.score(word)
		self.new(word).score
	end
end	

# additional tests for double_letter and double_word
=begin

	def test_double_letter
		assert_equal 17, Scrabble.new("xi", double_letter: ['x']).score
	end

	def test_tripple_letter
		assert_equal 20, Scrabble.new("key", tripple_letter: ['k']).score
	end

	def test_double_word
		assert_equal 26, Scrabble.new("alacrity", double_word: 1).score
	end

	def test_tripple_word
		assert_equal 66, Scrabble.new("quirky", tripple_word: 1).score
  end

	def test_double_letter_tripple_word
		assert_equal 45, Scrabble.new("key", 
																	double_letter: ['k'],
																	tripple_word: 1).score
	end

	def test_two_double_words
		assert_equal 40, Scrabble.new("aeioulnrst", double_word: 2).score
	end

	def test_four_double_words
		assert_equal 160, Scrabble.new("aeioulnrst", double_word: 4).score
	end

	def test_a_bunch_of_bonuses
		assert_equal 1836, Scrabble.new("aeioulnrst", 
															 double_letter: ['a', 'e', 'i'],
															 tripple_letter: ['o', 'u'],
															 double_word: 2,
															 tripple_word: 3).score
	end

=end
