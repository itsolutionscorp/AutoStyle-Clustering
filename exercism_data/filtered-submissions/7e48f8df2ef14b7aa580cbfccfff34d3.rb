def compute(a, b)
    first_strand  = a.split(//)
    second_strand = b.split(//)

    comparision = first_strand.zip(second_strand)
    comparision.count do |first_letter, second_letter|
      first_letter != second_letter unless second_letter.nil?
    end
  end