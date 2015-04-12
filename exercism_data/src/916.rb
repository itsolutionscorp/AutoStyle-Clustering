class Hamming
  def compute(a,b)
    count = a.length

    a.each_char.with_index {|char, i|
      count-=1 if char==b[i]
    }
    count
  end


end
