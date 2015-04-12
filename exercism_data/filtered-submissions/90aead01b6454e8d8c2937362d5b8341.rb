class Hamming
	class << self

		def compute(a, b)

			if a == b 

			 	return 0

			else 

				a_split = a.split('')
				array1 = []

					for x in a_split

						array1 << x

					end

				b_split = b.split('')
				array2 = []

					for x in b_split

						array2 << x

					end

				counter = 0

					if a.length > b.length

						longer = b.length-1

					else
						longer = a.length-1

					end


				for i in 0 .. longer

					if array1[i] != array2[i]

						counter += 1
					end

				end

				return counter

			end

		end

	end

end
