class School
	attr_reader :students

	def initialize
		@students = []
	end

	def add(name, grade)
		@students << Student.new(name, grade)
	end

	def to_hash
		studentshash = {}
		
		#fill the hash with empty arrays for each grade
		gradelevels.each { |grade| studentshash[grade] = [] }

		#put each student's name in the appropriate grade level
		students.each do |student|
			studentshash[student.grade] << student.name
		end
		studentshash
	end

	def grade(gradelevel)
		students.select {|s| s.grade == gradelevel}.map{|s| s.name}
	end

	def students
		@students.sort_by { |s| [s.grade, s.name] }
	end

	private
	def gradelevels
		students.map {|s| s.grade}.uniq
	end
end

class Student
	attr_reader :name, :grade

	def initialize(name,grade)
		@name = name
		@grade = grade
	end
end
