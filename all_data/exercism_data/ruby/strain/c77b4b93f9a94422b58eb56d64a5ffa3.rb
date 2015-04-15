class Array
	def keep
		self.keep_if { |item| yield(item) }
	end

	def discard
		self.reject { |item| yield(item) }
	end
end
