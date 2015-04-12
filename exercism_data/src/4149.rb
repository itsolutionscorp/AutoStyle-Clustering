def compute(first, second)
    h = 0
    first_array = first.split('')
    second_array = second.split('')

    first_array.map.with_index{ |l, i| h += 1 if second_array[i] != l unless i >= second_array.length }

    return h
  end