class Fixnum

  Numerals = %w(I V X L C D M . .)

  def to_roman
    return 'Nimis magnus est numerus' unless self < 4000
    result = []
    places.each_with_index { |p, i|
      current = []
      base = i*2

      letters(p).each_with_index { |l, j|
        qr = j.divmod 2
        current << Numerals[base + ((qr[0] * qr[1]) + qr[1])].chars * l
      }
      result << current
    }
    result.reverse.join
  end

  def places
    self.to_s.chars.reverse.map { |n| n.to_i }
  end

  def letters n
    result = [0, 0, 0, 0]
    if n == 4
      result = [1, 1, 0, 0]
    elsif n == 9
      result = [1, 0, 0, 1]
    else
      qr = n.divmod 5
      result[2 * qr[0]] = qr[1]
      result[1] = qr[0]
    end
    result
  end

end
