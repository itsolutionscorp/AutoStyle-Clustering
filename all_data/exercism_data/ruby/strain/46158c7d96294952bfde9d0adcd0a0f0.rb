class Array
	def keep(&sieve)
		self.keep_if { |item| yield(item) }
	end

	def discard(&sieve)
		self.reject { |item| yield(item) }
	end
end
