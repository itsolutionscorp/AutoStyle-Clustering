def compute(strand_1, strand_2)
    strand_1_array = strand_1.split('')
    strand_2_array = strand_2.split('')

    matches = strand_1_array.zip(strand_2_array)
    matches.delete_if { |acid_pair| acid_pair.include?(nil) }

    counter = 0

    matches.each do |acid_pair|
     if acid_pair[0] != acid_pair[1] && acid_pair.length == 2
        counter += 1
      end
    end

    counter
  end