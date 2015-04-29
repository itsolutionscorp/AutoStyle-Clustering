def compute(a, b)
    a = a.size > b.size ? a[0...-1] : a
    new_a = a.chars.zip b.chars
    new_a.map(&:uniq).delete_if {|a| a.count == 1 }.count
  end