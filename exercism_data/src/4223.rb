class Hamming

  def compute(a,b)

    aa = a.scan(/./)
    bb = b.scan(/./)
    ham = 0

    aa.zip(bb).each do |a, b|
      if a == b or b == nil
        ham
      else
        ham = ham + 1
      end
    end
    return ham
  end

end
