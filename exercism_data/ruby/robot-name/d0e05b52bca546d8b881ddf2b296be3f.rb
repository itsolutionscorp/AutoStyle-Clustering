require 'set'

module Name

	UPPER = Array('A'..'Z')
	LOWER = Array('a'..'z')
	DIGIT = Array('0'..'9')
	SLASH_W = DIGIT | UPPER |LOWER | ['_']
	
	module_function
	def robot
		select(2,SLASH_W) + select(3,DIGIT)
	end

	def select(number, symbols)
		word = ''
		number.times do |t|
			random_index = Random.new.rand(0..symbols.length-1)
		       	word << symbols[random_index]
		end
		word
	end
end

class Robot
	@@issued = Set.new []

	attr_reader :name
	
	def initialize
		@name = issue_name
 		ObjectSpace.define_finalizer( self, self.class.finalize(name) )
	end

	def reset
		old_name = @name
		@name = issue_name
		@@issued.delete(old_name)
	end

	def self.names
		@@issued
	end

	def self.finalize(name)
		proc { @@issued.delete(name) }

	end

	private

	def issue_name
		begin
			name = Name.robot 
		end until !issued?(name)
		@@issued.add name
		name
	end

	def issued?(name)
		@@issued.intersect?(Set.new [name])
	end
end
