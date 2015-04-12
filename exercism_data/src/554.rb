def compute(control, mutant)
    control_dna = control.chars
    mutant_dna  =  mutant.chars

    compare_length = [control_dna.length, mutant_dna.length].min - 1
    discrepancy_count = 0

    (0..compare_length).each do |n|
      discrepancy_count += 1 if control_dna[n] != mutant_dna[n]
    end

    discrepancy_count
  end