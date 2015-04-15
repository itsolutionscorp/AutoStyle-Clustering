#store students in a hash composed of sub arrays
class School
	def initialize
		@students = Hash.new{ |h,k| h[k] = [] }
	end

	def to_hash
		#this is to accomodate for printing grade ordered
		@students.sort.to_h
	end

	def add( name, grade )
		@students[grade] << name
		@students[grade].sort!
	end

	def grade( level )
		@students[level]
	end
end
