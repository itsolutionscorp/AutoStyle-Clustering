class Complement

	TRANSCRIPT = [['G', 'C'], ['C', 'G'], ['T','A'], ['A','U']]

  def self.of_dna(rna)
    strand_transition([0, 1], rna)
  end

	def self.of_rna(dna)
    strand_transition([1, 0], dna)
  end

  # Transfer chars according to the transcript dependend on direction
  def self.strand_transition(directions, strand)
    strand.split(//).each_with_index do |strand_el, index|
      TRANSCRIPT.each do |t_el|

        # If current char of old_strand matches the iterated transcript char replace it
        if strand_el == t_el[directions[0]]
          strand[index] = t_el[directions[1]]
          break
        end
      end
    end
    strand
  end

end
