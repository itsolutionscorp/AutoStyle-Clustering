class Garden

	attr_reader :alice, :students
	
	def initialize(plants, *students)
		@garden = plants.split("\n")
		raise ArgumentError.new('The two lines of the garden differ in length.') if @garden[0].length != @garden[1].length
		@students = if students.empty? then @students = %w{ alice bob charlie david eve fred ginny harriet ileana joseph kincaid larry}.sort! 
					elsif students[0].is_a?(Array) then students.flatten!.map! {|student| student.downcase!}.sort
					else students.map! {|student| student.downcase!}.sort
					end	
		@ownership = student_quartets
		student_quartets.keys.each do |student|
			self.class.send(:define_method, "#{student}") do	@ownership["#{student}"].map! {|p| PLANTS[p]}	end
		end
	end
	
	PLANTS = {'G' => :grass, 'C' => :clover, 'R' => :radishes, 'V' => :violets}
	
	def quartets
		window = 0
		room = 1
		number_of_quartets = if @garden[0].length % 2 == 0 then @garden[0].length / 2 else (@garden[0].length + 1) / 2 end
		quartets = Array.new
		for i in 0..number_of_quartets do
			quartets[i] = Array.new
			if @garden[window][2*i] 		then quartets[i] << @garden[window][2*i] 		end
			if @garden[window][2*i + 1] 	then quartets[i] << @garden[window][2*i + 1] 	end
			if @garden[room][2*i]			then quartets[i] << @garden[room][2*i]			end		
			if @garden[room][2*i + 1]		then quartets[i] << @garden[room][2*i + 1]		end
			quartets[i].reject!(&:empty?)
			quartets << quartets[i]
		end
		quartets.reject!(&:empty?)
		quartets
	end
	
	def student_quartets
		ownership = Hash.new
		@students.each_with_index do |student, index|
			ownership.merge! ({student => quartets[index]})
		end
		ownership
	end
	
	def plantNames(arr)
		arr.map! {|plant| PLANTS[plant]}
	end	
end
