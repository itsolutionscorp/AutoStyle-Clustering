require 'set'

class School
	def initialize
		@db = Hash.new { |h,k| h[k] = Set.new }
	end	
	
	def db
		return Hash[@db.map{|k,v| [k,v.to_a] } ]
	end

	def add(name, grade)
		@db[grade].add(name)
	end

	def sort
		return Hash[@db.map{|k,v| [k,v.to_a.sort] }.sort ]
	end

	def grade(grade)
		return @db[grade].to_a
	end
end
