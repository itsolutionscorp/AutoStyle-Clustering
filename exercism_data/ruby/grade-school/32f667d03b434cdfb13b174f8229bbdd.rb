class School

	def db
		@db ||= Hash.new
	end

	def grade grade
		db[grade] ||= Array.new
	end

	def sort
		h = db.clone
		h.each { |key, value| value.sort!}
		Hash[h.sort]
	end

	def add name, grade
		db[grade] ||= Array.new
		db[grade] << name
	end

end
