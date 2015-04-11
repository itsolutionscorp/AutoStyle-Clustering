class School
	def initialize
		@school_hash = {}
	end
	
	def add(name,grade)
		@school_hash.has_key?(grade)? @school_hash[grade] << name : @school_hash[grade] = [name]
	end

	def to_hash
		@school_hash.each do |k,v|
			@school_hash[k] = @school_hash[k].sort
		end
		Hash[@school_hash.sort]

	end

	def grade(input)
		@school_hash[input].nil? ? [] : @school_hash[input].sort  
	end

end
