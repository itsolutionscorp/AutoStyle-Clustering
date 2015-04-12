def compute(a, b)
    joined_strings = [a, b]

    length = joined_strings.min{ |a, b| a.length <=> b.length }.length
    array_a = a.chars.take(length)
    array_b = b.chars.take(length)

    array_c = array_a.zip(array_b)
    array_d = array_c.each {|pair| pair.uniq!}
    array_e = array_d.map {|x| x.count}
    array_e.delete_if {|i| i < 2}
    array_e.count
  end