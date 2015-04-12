class Hamming

  def compute(strain_a, strain_b)
    if strain_a.length > strain_b.length
      strain_a = strain_a[0..strain_b.length-1]
    end

    strain_a.chars.select.with_index do |c, i|
      strain_b[i] != strain_a[i]
    end.count
  end

end
