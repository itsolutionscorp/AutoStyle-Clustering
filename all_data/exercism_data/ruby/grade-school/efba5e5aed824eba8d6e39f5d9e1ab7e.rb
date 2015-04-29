class School
	def initialize
		@db = Hash.new()
	end
	def db
		@db
	end

	def add(student_name,student_grade)

      @db[student_grade] = @db[student_grade] || Array.new
    
      @db[student_grade] << student_name
      
	end

	def grade(grade)
		@db[grade] = @db[grade] || []
	end

	def sort
		h = Hash[@db.sort]
		h.each do |key,value|
			value.sort!
		end
		return h
	end
end
