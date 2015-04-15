class PigLatin

	VOWELS 	 = "aeiou"
	CONSONANTS = "bcdfghjklmnpqrstvwxyz"

	VOWEL_LIKE = { 
		prefixes: ["[#{VOWELS}]", "yt", "xr"],
		substitute: "%{a}ay",
		regex: '^(?<a>(%{prefix}).*)'
	}

	CONSONANT_LIKE = {
		prefixes: ["[#{CONSONANTS}]*qu", "[#{CONSONANTS}]+"],
		substitute: "%{b}%{a}ay", 
		regex: '^(?<a>%{prefix})(?<b>.*)',
	}

	@@conversions = {}

	def self.translate(phrase)
		phrase.split.map { |word| @@conversions[word] }.join(" ")
	end

	private

		def self.addConversions(group)
			prefix = group[:prefixes].join('|')
			reg = Regexp.new(group[:regex] % {prefix: prefix})
			@@conversions = @@conversions.merge({reg => group[:substitute]})
		end

		def self.make_hash_key_match_interpolate_return_value
			@@conversions.default_proc = lambda do |hash, word|
				hash.each_pair do |key, value|
					if key =~ word
						captures = $~.names.map(&:to_sym).zip($~.captures).to_h
						return value % captures
					end
				end
				return "#{word}ay" # no match
			end
		end

		def self.setup
			[VOWEL_LIKE, CONSONANT_LIKE].each do |type|
				addConversions type
			end

			make_hash_key_match_interpolate_return_value
		end

		self.setup

end
