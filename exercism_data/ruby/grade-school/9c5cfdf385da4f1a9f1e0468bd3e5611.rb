class School
	attr_reader :db
	def initialize
		@db = {}
	end

	def add(arg1, arg2)
		db[arg2] = db[arg2].to_a << arg1
	end

	def grade(num)
		db[num].to_a
	end

	def sort
		temp = {}
		db.keys.sort.each { |k| temp[k] = db[k].sort}
		temp
	end
end
