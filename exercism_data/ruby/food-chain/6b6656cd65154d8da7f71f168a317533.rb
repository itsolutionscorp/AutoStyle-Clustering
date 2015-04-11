class FoodChainSong
	def initialize
		@common_lines = [
			"I don't know why she swallowed the fly. Perhaps she'll die.\n",
			"She swallowed the spider to catch the fly.\n",
			"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
			"She swallowed the cat to catch the bird.\n",
			"She swallowed the dog to catch the cat.\n",
			"She swallowed the goat to catch the dog.\n",
			"She swallowed the cow to catch the goat.\n"		
		]
		@verse_lines = [
			"I know an old lady who swallowed a fly.\n",
			"I know an old lady who swallowed a spider.\nIt wriggled and jiggled and tickled inside her.\n",
			"I know an old lady who swallowed a bird.\nHow absurd to swallow a bird!\n",
			"I know an old lady who swallowed a cat.\nImagine that, to swallow a cat!\n",
			"I know an old lady who swallowed a dog.\nWhat a hog, to swallow a dog!\n",
			"I know an old lady who swallowed a goat.\nJust opened her throat and swallowed a goat!\n",
			"I know an old lady who swallowed a cow.\nI don't know how she swallowed a cow!\n",
			"I know an old lady who swallowed a horse.\nShe's dead, of course!\n"
		]
	end

	def verse(n)
		@verse_lines[n-1] + (n < 8 ? @common_lines[0..n-1].reverse.join : "")
	end

	def verses(n,m)
		[*n..m].reduce(""){|song, v| song + verse(v) + "\n"}
	end

	def sing
		verses(1, @verse_lines.length)
	end
end
