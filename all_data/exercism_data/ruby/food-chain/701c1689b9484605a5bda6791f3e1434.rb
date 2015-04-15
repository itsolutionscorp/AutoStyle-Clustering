class FoodChainSong
	WHAT_THE_SPIDER_DID = 'wriggled and jiggled and tickled inside her'
	
	ANIMALS = {
		'fly'			=> nil,
		'spider' 	=> "It #{WHAT_THE_SPIDER_DID}.",
		'bird' 		=> "How absurd to swallow a bird!",
		'cat'			=> "Imagine that, to swallow a cat!",
		'dog'			=> "What a hog, to swallow a dog!",
		'goat'		=> "Just opened her throat and swallowed a goat!",
		'cow'			=> "I don't know how she swallowed a cow!",
		'horse'		=> "She's dead, of course!"
	}
	
	def verse(number)
		animal = ANIMALS.keys[number-1]
		verse = swallowed(animal)
		verse << witty_remark(animal) unless (animal == 'fly')
		verse << food_chain(number) unless (animal == 'horse')
		verse
	end
	
	def verses(from, to)
		(from..to).to_a.map { |n| "#{verse(n)}\n" }.join
	end
	
	def sing
		verses(1, 8)
	end
	
private
	def swallowed(animal)
		"I know an old lady who swallowed a #{animal}.\n"
	end
	
	def witty_remark(animal)
		"#{ANIMALS[animal]}\n"
	end
	
	def food_chain(number)
		ANIMALS.keys.take(number).reverse.map do |animal|
			if animal == 'fly'
				"I don't know why she swallowed the fly. Perhaps she'll die.\n"
			else
				"She swallowed the #{animal} to catch the #{smaller_than(animal)}.\n"
			end
	end.join
	end
	
	def smaller_than(animal)
		result = ANIMALS.keys[ANIMALS.keys.index(animal)-1]
		result == 'spider' ? "#{result} that #{WHAT_THE_SPIDER_DID}" : result
	end
end
