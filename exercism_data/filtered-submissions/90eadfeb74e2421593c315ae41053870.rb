class Hamming
  def compute(first, last)
    short, long = [first,last].sort_by!(&:size)
    short.chars.zip(long.chars).map do |a,b|
      if a == b
        0
      else
        1
      end
    end.inject(:+)
  end
end
