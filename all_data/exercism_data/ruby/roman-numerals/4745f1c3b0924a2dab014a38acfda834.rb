class Fixnum

  def to_roman
    thou=(self/1000).floor
    hund=(self-(thou*1000))/100.floor
    tens=(self-(thou*1000)-(hund*100))/10.floor
    ones=self-(thou*1000)-(hund*100)-(tens*10)
    txt=['','M','MM','MMM'][thou]
    txt+=['','C','CC','CCC','CD','D','DC','DCC','DCCC','CM'][hund]
    txt+=['','X','XX','XXX','XL','L','LX','LXX','LXXX','XC'][tens]
    txt+=['','I','II','III','IV','V','VI','VII','VIII','IX'][ones]
  end

end
