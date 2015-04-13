def compute(*strands)
    smallest_strand_length = strands.min.size.to_i
    strands.map! { |strand| strand.split('').slice(0..smallest_strand_length) }
    zipped_strands = strands[0].zip(strands[1])
    zipped_strands.delete_if { |strand| strand[0] == strand[1] || strand.include?(nil)}
    zipped_strands.size
  end