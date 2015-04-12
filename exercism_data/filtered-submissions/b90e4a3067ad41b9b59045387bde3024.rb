class Hamming
  def compute(strand_x,strand_y)
    min_length = [strand_x.size, strand_y.size].min
    (0...min_length).count { |strand| strand_x[strand] != strand_y[strand] }
  end
end
