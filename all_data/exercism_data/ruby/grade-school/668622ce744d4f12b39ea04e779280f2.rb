class School

	def initialize
		@students = Hash.new {|h,k| h[k] = [] }
	end

	def db
		@students
	end

	def add(value, key)
		@students[key].push(value)
		@students
	end

	def grade(num)
		@students[num]
	end

	def sort
		sorted = Hash[@students.sort]
		sorted.values.each {|x| x.sort!}
		sorted
	end



end

# {3=>["Kyle"], 4=>["Christopher", "Jennifer"], 6=>["Kareem"]}
