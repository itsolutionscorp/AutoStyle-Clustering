class FoodChainSong
	def initialize
		@intro = Array.new(9,"")
		@chorus = Array.new(9,"")
		@intro[1]	= "I know an old lady who swallowed a fly.\n"
		@chorus[1]	= "I don't know why she swallowed the fly. Perhaps she'll die.\n"
		@intro[2]	=	"I know an old lady who swallowed a spider.\n" + "It wriggled and jiggled and tickled inside her.\n"
		@chorus[2]	= "She swallowed the spider to catch the fly.\n"
		@intro[3]	=	"I know an old lady who swallowed a bird.\n" + "How absurd to swallow a bird!\n"
		@chorus[3]	=	"She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n"
		@intro[4]	=	"I know an old lady who swallowed a cat.\n" + "Imagine that, to swallow a cat!\n"
		@chorus[4]	=	"She swallowed the cat to catch the bird.\n"
		@intro[5]	=	"I know an old lady who swallowed a dog.\n" + "What a hog, to swallow a dog!\n" 
		@chorus[5]	=	"She swallowed the dog to catch the cat.\n"
		@intro[6]	= "I know an old lady who swallowed a goat.\n" + "Just opened her throat and swallowed a goat!\n"
		@chorus[6]	=	"She swallowed the goat to catch the dog.\n"
		@intro[7]	= "I know an old lady who swallowed a cow.\n" + "I don't know how she swallowed a cow!\n"
		@chorus[7]	=	"She swallowed the cow to catch the goat.\n"
		@intro[8]	=	"I know an old lady who swallowed a horse.\n" 
		@chorus[8]	= "She's dead, of course!\n"
	end
	def verse(verse_number)
			@intro[verse_number] + verse_recurse(verse_number) 
	end
	def verse_recurse(verse_number)
		if verse_number < 8 && verse_number > 0 then
			@chorus[verse_number] + verse_recurse(verse_number - 1) 
		else
			@chorus[verse_number]
		end
	end
	def verses(first, last)
		lyrics = ''
		(first..last).each do |n|
			lyrics += verse(n) + "\n"
		end
		lyrics
	end
	def sing
		verses(1,8)
	end
end
