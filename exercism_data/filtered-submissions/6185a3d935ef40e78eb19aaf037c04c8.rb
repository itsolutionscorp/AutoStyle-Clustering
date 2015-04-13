def compute a_str, b_str
    a, b = a_str.split(''), b_str.split('')
    a.zip(b).count { |sym_a, sym_b| sym_a != sym_b }
  end