def compute(dna_a,dna_b)


    dnas_in_order = [dna_a, dna_b].sort_by{|dna| dna.length}
    dnas_as_arrays = dnsas_in_order.map{|dna| dna.split("")}
    comparisons = dnas_as_arrays.first.zip dnas_as_arrays.second
    differences = comparisons.select{|a,b| a!= b}.count
  end