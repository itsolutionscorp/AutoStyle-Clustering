class Hamming
  def compute(arg_left, arg_right)
    (0...[arg_left.length, arg_right.length].min).count { |i| arg_left[i] != arg_right[i] }
  end
end
