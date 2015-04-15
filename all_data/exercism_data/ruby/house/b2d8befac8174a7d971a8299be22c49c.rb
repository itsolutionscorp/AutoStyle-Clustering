class House
	@lines = 
	[
		['lay in', 'house that Jack built.'],
		['ate', 'malt'],
		['killed', 'rat'],
		['worried', 'cat'],
		['tossed', 'dog'],
		['milked', 'cow with the crumpled horn'],
		['kissed', 'maiden all forlorn'],
		['married', 'man all tattered and torn'],
		['woke', 'priest all shaven and shorn'],
		['kept', 'rooster that crowed in the morn'],
		['belonged to', 'farmer sowing his corn'],
		[nil, 'horse and the hound and the horn'],
	]

	def self.recite()
		[].tap do |song|
			(0...@lines.length).each { |i| song << get_verse(i) }
		end.join("\n")
	end

	private

	def self.get_verse(num)
		[].tap do |verse|
			num.downto(0) { |i| verse << get_line(i, i==num)}
		end.join("\n") + "\n"
	end

	def self.get_line(num, is_first_line)
		return "This is the #{@lines[num][1]}" if is_first_line
		"that #{@lines[num][0]} the #{@lines[num][1]}"
	end
end
