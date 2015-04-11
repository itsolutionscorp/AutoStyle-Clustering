DIRECT_OBJECT= %w(fly spider bird cat dog goat cow horse)

BUG_OBJECT = "spider"
DOES_SOMETHING = "wriggled and jiggled and tickled inside her.\n"

TO_DO_THAT= "to swallow a %s!\n"
DID_IT = "swallowed a %s!\n"

REFRAIN_START = [["",""],
		 ["It ", DOES_SOMETHING],
		 ["How absurd ", TO_DO_THAT],
		 ["Imagine that, ",TO_DO_THAT],
		 ["What a hog, ", TO_DO_THAT],
	         ["Just opened her throat and ", DID_IT],
		  ["I don't know how she ",DID_IT],
		  ["",""]]
REFRAIN_MAP = Hash[DIRECT_OBJECT.zip(REFRAIN_START)]
REFRAIN = "She swallowed the %s to catch the %s.\n"

START_VERSE = "I know an old lady who swallowed a %s.\n"
END_VERSE = "I don't know why she swallowed the %s. Perhaps she'll die.\n"

END_SONG = "She's dead, of course!\n"

COMMENT = 0
ON_ACTION = 1
OBJECT = '%s'

class Refrain
	def verse(number)
		lines = first_line(number)
		lines << cumulate(number)
	end

	private

	def first_line(nr)
		lines = ""
		lines += REFRAIN_MAP[DIRECT_OBJECT[nr]][COMMENT]
		action = REFRAIN_MAP[DIRECT_OBJECT[nr]][ON_ACTION]
		if action.include?(OBJECT)
			lines << action % DIRECT_OBJECT[nr]
		else
			lines << action
		end
		lines
	end

	def cumulate(nr)
		return "" if DIRECT_OBJECT[nr] == DIRECT_OBJECT.last
		lines = ''
		direct_object = DIRECT_OBJECT[0..nr].reverse
		for index in 0..direct_object.length - 2
		       if direct_object[index+1] == BUG_OBJECT
			       lines << ((REFRAIN[0..-3] + " that " + DOES_SOMETHING) % \
					 [direct_object[index], direct_object[index+1]])
		       else
			       lines << (REFRAIN % [direct_object[index], direct_object[index+1]])
		       end
		end
		lines
	end
end

class FoodChainSong
	def verse(number)
		lines = first_line(number.pred)
		lines << refrain(number.pred) if number > 1
		lines << last_line(number.pred)
	end

	def verses(from, to)
		lines = ""
		for index in from..to
			lines << verse(index)
			lines << "\n" 
		end
		lines
	end

	def sing
		verses(1,8)
	end
	
	private	
	def first_line(verse_nr)
		START_VERSE % [DIRECT_OBJECT[verse_nr]]	
	end

	def refrain(verse_nr)
		Refrain.new.verse(verse_nr)
	end

	def last_line(verse_nr)
		if DIRECT_OBJECT[verse_nr] != DIRECT_OBJECT.last
			return END_VERSE % DIRECT_OBJECT[0]
		else
			return END_SONG
		end
	end

end
