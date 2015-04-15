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

						array2.each_index do |i|

							if array1[i] != array2[i]

								counter += 1
							end

						end

					else

						array1.each_index do |i|

							if array1[i] != array2[i]

								counter += 1
							end
						end

					end

				return counter

			end
		end