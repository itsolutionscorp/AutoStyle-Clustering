class Integer

  def to_roman
    n = self
    a2r = [
           [1000,'M'],[900,'CM'],[500,'D'],[400,'CD'],
           [100,'C'],[90,'XC'],[50,'L'],[40,'XL'],
           [10,'X'],[9,'IX'],[5,'V'],[4,'IV'],
           [1,'I']
          ]
    a2r.inject('') do |r, (a,ch)|
      r << ch*(n/a)
      n = n.remainder a
      r
    end
  end

end
