class Hamming

  def Hamming.compute(a_str, b_str)
    count = 0
    a_chars = a_str.chars.to_a
    b_chars = b_str.chars.to_a

    a_chars.each_with_index do |a, i|
      break if b_chars[i].nil?
      count += 1 if a != b_chars[i]
    end

    return count
  end

end
