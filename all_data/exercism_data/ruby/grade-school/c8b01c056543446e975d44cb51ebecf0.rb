class School
	attr_accessor :db

	def initialize
		@db = {}
	end

	def add(name, grade)
		db[grade] = db[grade] ? db[grade] << name : [name]
	end

	def grade(grade)
		db[grade] || []
	end

	def sort
		db.keys.sort.inject({}) { |res, k| res.merge(k => db[k].sort) }
	end
end
