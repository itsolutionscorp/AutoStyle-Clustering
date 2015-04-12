class Hamming

  # Computes the hamming distance between two strands.
  # Return <code>nil</code> for invalid parameters.
	def compute(strand1, strand2)
		result    = nil
    params_ok = (strand1.is_a?(String) and strand2.is_a?(String))

    if params_ok
      result  = 0
      length1 = strand1.length
      length2 = strand2.length

      if (length1 > 0) and (length2 > 0)
        min_length = [length1, length2].min

        (0...min_length).each do |index|
          if (strand1[index] != strand2[index])
            result += 1
          end
        end
      end
    end

		result
	end

end
