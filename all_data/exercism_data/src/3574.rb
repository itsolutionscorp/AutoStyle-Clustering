def compute(dna_a,dna_b)
    #min_length = [dna_a.length, dna_b.length].min 
    #differences = (0..min_length-1).select{|index| dna_a[index] != dna_b[index]}.count
    dnas_in_order = [dna_a, dna_b].sort_by{|dna| dna.length}
    dnas_as_arrays = dnsas_in_order.map{|dna| dna.split("")}
    comparisons = dnas_as_arrays.first.zip dnas_as_arrays.second
    differences = comparisons.select{|a,b| a!= b}.count
  end