class Fixnum
  def to_roman    
    digits = self.to_s.reverse.split('').map{ |i| i.to_i }
    symbols = [['I','V','X'], ['X','L','C'], ['C','D','M'], ['M', 'unknown', 'unknown']]
    roman = ''
    (0...digits.length).each do |i|
      s = symbols[i]
      tmp = case digits[i]
              when 1 then s[0]
              when 2 then s[0]*2
              when 3 then s[0]*3
              when 4 then s[0]+s[1]
              when 5 then s[1]
              when 6 then s[1]+s[0]
              when 7 then s[1]+(s[0]*2)
              when 8 then s[1]+(s[0]*3)
              when 9 then s[0]+s[2]
              else ''
            end
        roman = tmp + roman
    end
    roman
  end
end
