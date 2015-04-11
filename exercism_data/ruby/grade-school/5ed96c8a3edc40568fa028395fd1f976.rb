class School
	def initialize
		@db = Hash.new  { |hash, key| hash[key] = [] }
	end

	def to_hash
		Hash[@db.sort.map{|key, value| [key, value.sort]}]
	end

	def add(who, gr)
		@db[gr] << who
	end

	def grade(gr)
		@db[gr].sort
	end
end
