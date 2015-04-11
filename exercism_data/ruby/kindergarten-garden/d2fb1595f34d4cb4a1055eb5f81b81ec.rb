class Garden

	DEFAULT_STUDENTS = %w{Alice Bob Charlie David 
				  Eve Fred Ginny Harriet
				  Ileana Joseph Kincaid Larry}

	PLANTS = {
		"G" => :grass,
		"C" => :clover,
		"R" => :radishes,
		"V" => :violets,
	}

	def initialize(garden, names = DEFAULT_STUDENTS)
		@garden = garden
		self.students = names
		@garden_info = process_garden
	end

	def students
		@students
	end

	def students=(names)
		process_student_methods(:remove) if @students
		@students = names.sort
		process_student_methods(:define)
	end

	private

		def process_student_methods(method)
			method = (method.to_s + "_method").to_sym

			@students.map(&:downcase).each do |name|
				self.class.send(method, name) { student(name) }
			end	
		end

		def student(name)
			@garden_info[name]
		end

		def rows
			@rows ||= @garden.split("\n").map do |row| 
				row.ljust(students.length*2, " ") 
			end
		end

		def process_garden
			Hash[students.map.with_index do |s, i| 
				[s.downcase, plants_for_student(s, i)] 
			end]
		end

		def plants_for_student(student, i)
			range = (i*2)...(i*2+2)
			plants = rows.map do |row|
				row[range].split('').map { |char| PLANTS[char] }.compact
			end.flatten
		end
	
end
