class Hamming
  def compute(arg_left, arg_right)
    (0...arg_left.length).count { |i| arg_left[i] != arg_right[i] }
  end
end
