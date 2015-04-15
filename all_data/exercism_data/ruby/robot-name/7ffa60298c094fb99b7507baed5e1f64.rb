require 'set'

class Robot 

	@@names = Set.new
	@cur_name = ""

	def initialize
		set_name
	end

	def name
		@cur_name
	end

	def reset
		set_name
	end

	private

	def gen_name
		word = (0..1).map do ('a'..'z').to_a[rand(26)] end
		word << (0..2).map do rand(9) end	
		word.join
	end

	def set_name 
		begin
			@cur_name = gen_name
		end while(@@names.add?(@cur_name) == nil)
	end

end
