class Fixnum
  def to_roman
    out = ""
    self.times do
      out = out + "I"
      out.gsub!('XMVIIII','IM')
      out.gsub!('CMLXXXVIIII', 'IXM')
      out.gsub!('XCMVIIII', 'ICM')
      out.gsub!('DCCCLXXXVIIII','IXCM')
      out.gsub!('XDVIIII', 'ID')
      out.gsub!('CDLXXXVIIII', 'IXD')
      out.gsub!('XCDVIIII', 'ICD')
      out.gsub!('CCCLXXXVIIII', 'IXCD')
      out.gsub!('XCVIIII','IC')
      out.gsub!('LXXXVIIII','IXC')
      out.gsub!('XLVIIII','IL')
      out.gsub!('XXXVIIII', 'IXL')
      out.gsub!('VIIII','IX')
      out.gsub!('IVI','V')
      out.gsub!('IXI','X')
      out.gsub!('IXLI','XL')
      out.gsub!('ILI', 'L')
      out.gsub!('IXCI','XC')
      out.gsub!('ICI','C')
      out.gsub!('IXCDI','XCD')
      out.gsub!('ICDI','CD')
      out.gsub!('IXDI','XD')
      out.gsub!('IDI','D')
      out.gsub!('IXCMI','XCM')
      out.gsub!('ICMI','CM')
      out.gsub!('IXMI','XM')
      out.gsub!('IMI','M')
      out.gsub!('IIII','IV')
    end
    out
  end
end
