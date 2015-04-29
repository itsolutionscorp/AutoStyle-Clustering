class School

	attr_reader :db
	
	def initialize
		@db = {}
	end

	def grade(grade_number)
		@db[grade_number] ||= []
	end
		
	def add(name, grade_number)
		grade(grade_number) << name
	end

	def sort
		@db.each{|key,value| @db[key].sort!}
		Hash[@db.sort]
	end

end
