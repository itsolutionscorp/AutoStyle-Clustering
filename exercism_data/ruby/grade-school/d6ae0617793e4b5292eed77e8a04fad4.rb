class School
	attr_reader :db 

	def initialize
		@db = Hash.new( [] )
	end
		
	def add( name, year )
		@db[year] += [name]
	end

	def grade( year )
		@db[year]
	end

	def sort
		@db.each_value { |names| names.sort! }
		@db = Hash[@db.sort]
	end

	def keys
		@db.keys.sort
	end
end
