class School
	def initialize
		@roster = Hash.new()
	end
	
	def add(name, grd)
		if @roster[grd] == nil then 
			@roster[grd] = [name]
		else
			@roster[grd] << name
			@roster[grd].sort!
		end
	end
	
	def to_hash
		Hash[@roster.sort]
	end
	
	def grade(grd)
		return @roster[grd] unless @roster[grd] == nil
		[]
	end
end
