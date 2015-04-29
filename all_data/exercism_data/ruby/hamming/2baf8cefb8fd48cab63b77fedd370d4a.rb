class Hamming
  def Hamming.compute(strand_x, strand_y)
    Hamming.distance(strand_x, strand_y, 0)
  end

  private

  # Tail recursive version of distance calculation
  def Hamming.distance(strand_x, strand_y, current_distance)
    if(strand_x.empty? or strand_y.empty?)
      current_distance
    else
      Hamming.distance(strand_x[1..-1], strand_y[1..-1],
                       current_distance + Hamming.diff(strand_x[0], strand_y[0]))
    end
  end

  def Hamming.diff(char_x, char_y)
    if(char_x != char_y)
      1
    else
      0
    end
  end
end
