class Array

	def keep(&block)
		conditional = self.map &block
		answer = []
		conditional.each_with_index {|value, index| answer << self[index] if value}
		answer
	end

	def discard(&block)
		conditional = self.map &block
		answer = []
		conditional.each_with_index {|value, index| answer << self[index] if !value}
		answer
	end

end
