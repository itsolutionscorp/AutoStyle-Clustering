class Hamming
  def compute(str_a, str_b)
    str_a_array = str_a.split('')
    str_b_array = str_b.split('')

    str_length = str_a_array.length

    index = 0
    mismatches = 0

    str_length.times do
      if str_a_array.at(index) == str_b_array.at(index)
        mismatches += 1
      end
      index += 1
    end

    return str_length - mismatches
  end
end
