class Robot
	def name
		@name ||= generate_name
	end

	def reset
		@name = nil
	end

	private

	def generate_name
		(random_array("A","Z",2) + random_array(0,9,3)).join
	end

	def random_array(start, stop, number)
		Array.new(number) {[*start..stop].sample}
	end
end
