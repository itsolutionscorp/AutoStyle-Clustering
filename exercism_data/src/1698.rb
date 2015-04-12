def compute(reference_strand, new_strand)
    reference_strand.chars.zip(new_strand.chars).count {|(s1, s2)| s1 != s2 && s2}
  end
end