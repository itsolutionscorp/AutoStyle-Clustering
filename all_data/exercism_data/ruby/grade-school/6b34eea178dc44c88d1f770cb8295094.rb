class School
	attr_reader :local_database
	def initialize
		@local_database = Hash.new() {|hash, key| hash[key] = [] }
	end

	def db
		local_database
	end

	def add(name, grade)
		local_database[grade] << name
	end

	def grade(year)
		local_database[year]
	end

	def sort
		local_database.sort.each_with_object({}) do |result, grade| 
			grade[result[0]] = result[1].sort 
		end
	end

end
