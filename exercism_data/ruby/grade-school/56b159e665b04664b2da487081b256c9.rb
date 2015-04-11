class School
	def initialize
		@roster = {}
	end
	def to_hash
		result = {}
		@roster.keys.sort.each do |key|
			result[key] = @roster[key].sort
		end
		result
	end
	def add(name, grade_number)
		@roster[grade_number] = grade(grade_number).push name
	end
	def grade(grade_num)
		if @roster[grade_num] != nil then
			@roster[grade_num].sort
		else
			[]
		end
	end
end
