class School
	
	attr_reader :students

	def initialize
		@students = Hash.new { |h,k| h[k] = [] }
	end
	
	def db
		students
	end

	def add(name, count)
		db[count].push(name)
	end

	def grade(num)
		db[num]
	end

	def sort
		sorted = Hash[db.sort]
		sorted.values.each {|x| x.sort!}
		sorted
	end

end


 
