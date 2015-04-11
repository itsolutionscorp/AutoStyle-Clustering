DEFAULT_STUDENTS = 
[
	'alice', 'bob', 'charlie', 
	'david', 'eve', 'fred', 
	'ginny', 'harriet', 'ileana', 
	'joseph', 'kincaid', 'larry'
]

PLANTS = 
{
	'G' => :grass, 
	'C' => :clover, 
	'R' => :radishes,
	'V' => :violets,
}

class Garden
	def initialize(plants, students=DEFAULT_STUDENTS)
		@plants = plants.split("\n")
		@students = students.map(&:downcase).sort
	end
	
	def method_missing(meth, *args, &block)
		i = @students.index(meth.to_s)
		super unless i && i * 2 < @plants[0].size
		
		@plants.each_with_object([]) do |row, out| 
			row[i*2..i*2+1].each_char{|p| out << PLANTS[p]}
		end
	end
end
