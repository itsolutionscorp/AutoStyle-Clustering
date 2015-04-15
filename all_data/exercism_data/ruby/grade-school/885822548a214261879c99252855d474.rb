require 'set'

class School
	def initialize
		@db = {}
	end

	def add(student, year)
		@db[year] = grade(year).to_set.add(student).to_a
	end

	def db
		@db
	end

	def grade(year)
		@db[year] || []
	end

	def sort
		Hash[@db.sort.map{|k,v| [k, v.sort]}]
	end
end
