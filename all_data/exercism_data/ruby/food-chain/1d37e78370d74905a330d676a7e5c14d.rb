class FoodChainSong
	LYRICS = [
		["fly", nil, "I don't know why she swallowed the fly. Perhaps she'll die."],
		["spider", "It wriggled and jiggled and tickled inside her.", "She swallowed the spider to catch the fly."],
		["bird", "How absurd to swallow a bird!", "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her."],
		["cat", "Imagine that, to swallow a cat!", "She swallowed the cat to catch the bird."],
		["dog", "What a hog, to swallow a dog!", "She swallowed the dog to catch the cat."],
		["goat", "Just opened her throat and swallowed a goat!", "She swallowed the goat to catch the dog."],
		["cow", "I don't know how she swallowed a cow!", "She swallowed the cow to catch the goat."],
		["horse", "She's dead, of course!", nil],
	]

	def verse(num)
		lines = []

		lines << "I know an old lady who swallowed a #{LYRICS[num - 1][0]}."
		lines << LYRICS[num - 1][1] if LYRICS[num - 1][1]
		(num - 1).step(0, -1).each{|i| lines << LYRICS[i][2] if LYRICS[i][2]} if LYRICS[num - 1][2]
		lines << ''

		lines.join("\n")
	end

	def verses(from, to)
		Array(from..to).collect{|i| verse(i)}.join("\n") + "\n"
	end

	def sing
		verses 1, LYRICS.length
	end
end
