class Hamming
  def Hamming.compute(strand_x, strand_y)
    [strand_x.size,strand_y.size].min.times.map{ |c|
      Hamming.diff(strand_x[c], strand_y[c])
    }.reduce(0,:+)

  end

  private

  def Hamming.diff(char_x, char_y)
    if(char_x != char_y)
      1
    else
      0
    end
  end
end
