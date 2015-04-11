class School < Hash

	def add(student, grade)
		self[grade] = [] unless self[grade]
		self[grade] << student
	end

	def to_hash
		self.keys.sort.each.inject(School.new) do |sorted, key| 
			sorted[key] = grade(key)
		 	sorted
		end	
	end

	def grade(grade)
		self[grade] ? self[grade].sort : []
	end
end
