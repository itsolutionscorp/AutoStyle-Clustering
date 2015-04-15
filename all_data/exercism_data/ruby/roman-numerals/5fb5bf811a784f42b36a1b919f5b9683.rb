class Fixnum

  def to_roman
    syms = [[1000,'M'],[900,'CM'],[500,'D'],[400,'CD'],[100,'C'],[90,'XC'],[50,'L'],[40,'XL'],[10,'X'],[9,'IX'],[5,'V'],[4,'IV'],[1,'I']]
    i = self
    ret = ''
    
    syms.each do |sym|
      while i >= sym[0] 
        ret += sym[1]
        i -= sym[0]
      end
    end    

    ret
  end

end
