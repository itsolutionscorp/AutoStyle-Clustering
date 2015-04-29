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
		gradelevels.each do |grade|
			studentshash[grade] = []
		end
		students.each do |student|
			studentshash[student.grade] << student.name
		end
		studentshash.each do |grade, students|
			studentshash[grade] = students.sort
		end
	end

	def grade(gradelevel)
		students.select {|s| s.grade == gradelevel}.map{|s| s.name}.sort
	end

	private
	def gradelevels
		students.map {|s| s.grade}.uniq.sort
	end
end

class Student
	attr_reader :name, :grade

	def initialize(name,grade)
		@name = name
		@grade = grade
	end

end
