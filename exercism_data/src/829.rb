def compute(sequence1, sequence2)
    hi_index = ((l1 = sequence1.length) < (l2 = sequence2.length) ? l1 : l2) - 1
      # Hey- is it important to avoid calling String#length or does Ruby store
      # that? (Scanning string.c looks like it stores length.) But docs don't
      # say you can count on that. Above I store them to prevent O(n)
      # recomputation. Would this bother anyone else, too?

    # Accumulate differences across the common string position indices
    (0..hi_index).reduce(0) do |distance, index|
      sequence1[index] == sequence2[index] ? distance : distance + 1
    end
      # Hey- using a Range should generate the sequence for #reduce on-demand,
      # right? It won't expand an array just store explicit indices will it?
      # If this were comparing very long strings then it would matter... 
  end
end