def compute(a, b)
    a_array = a.split('')
    b_array = b.split('')
    shortest_len = a_array.length > b_array.length ? b_array.length : a_array.length 
    results = []
    0.upto(shortest_len - 1) { |i| results << (a_array[i] == b_array[i] ? true : false) }
    results.count(false)
  end