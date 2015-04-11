class Grains

	def square n
		2 ** (n - 1)
	end

	def total
		# fastest:
		# 2 ** 64 - 1

		# another option:
		# (1..65).inject { |sum, n| square n } - 1

		# most human-friendly
		(1..64).map { |n| square n }.inject &:+
	end

end
