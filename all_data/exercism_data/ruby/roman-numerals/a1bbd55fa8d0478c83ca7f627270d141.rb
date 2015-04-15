class Fixnum

  ROMAN = [['M','C','X','I'],['','D', 'L', 'V'],['','CD', 'XL', 'IV'], ['','CM','XC','IX']]

  def to_roman
    le = self.to_s.length
    self.to_s.split(//).each_with_index.map do |x,index|
      if x.to_i != 0
        index += (4-le) if le < 4
        case x.to_i
          when  1...4
            ROMAN[0][index] * x.to_i
          when 4
            ROMAN[2][index]
          when  9
            ROMAN[3][index]
          else
            ROMAN[1][index]+ROMAN[0][index]*(x.to_i-5)
        end
      end
    end.join()
  end


end

puts 2301.to_roman
