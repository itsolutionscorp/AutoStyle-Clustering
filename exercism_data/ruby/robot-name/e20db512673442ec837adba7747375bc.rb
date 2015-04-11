class Robot
	attr_reader :name
	
	@@inventory = []

	def initialize
		@name ||= rand_letter + rand_letter + Random.new.rand(100..999).to_s
		no_duplicates
	end
	
	def reset
		@name = rand_letter + rand_letter + Random.new.rand(100..999).to_s
		no_duplicates
	end
	
	def rand_letter
		letters = []
		"a".upto("z") {|x| letters << x}
		"A".upto("Z") {|x| letters << x}
		letters[Random.new.rand(0..51)]
	end
	
	def no_duplicates
		reset if @@inventory.include? (@name)
		@@inventory << @name
	end
end
