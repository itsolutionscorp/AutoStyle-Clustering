class DNA

	def initialize(genetics)
		@genetics = genetics
	end

	def to_rna
		@genetics.gsub("T", "U")
	end

end
