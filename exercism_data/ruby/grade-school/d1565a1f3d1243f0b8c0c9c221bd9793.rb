require 'pry'

class School
	def initialize
		@students = Hash.new { |h,k| h[k] = [] }
	end
	
	def db
		students
	end

	def add(name, count)
		students[count].push(name)
	end

	def grade(num)
		students[num]
	end

	def sort 
		students.keys.sort.each_with_object({}) { | key, obj |obj[key] = students[key].sort }
	end

	private

	attr_reader :students

end
 
