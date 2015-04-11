class Matrix

	def initialize(text)
		@text = text
	end

	def rows
		@text.split("\n").map do |single_row|
      single_row.split(' ').map(&:to_i)
    end
	end

	def columns
		rows.transpose
	end

end
