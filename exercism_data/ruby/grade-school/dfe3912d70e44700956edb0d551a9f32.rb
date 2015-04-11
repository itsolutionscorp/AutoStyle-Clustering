class School
	
	def initialize
		@database = Hash.new([])
	end

  	def add(name, grade)
		@database[grade] += [name]
	end

	def to_hash
		Hash[@database.each_with_object({}) {|(k,v), h| h[k] = v.sort}.sort]
	end

	def grade(grade)
		@database[grade].sort
	end

end
