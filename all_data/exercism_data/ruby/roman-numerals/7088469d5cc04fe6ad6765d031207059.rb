class Integer < Numeric
  def to_roman
    roman_divs = [1000,500,100,50,10,5,1]
    roman_nums = ['M','D','C','L','X','V','I']
    str = ""
    val = self
    roman_divs.each_with_index do |f, idx|
      divs = val / f
      
      up2 = roman_divs[idx+2]
      if val < f then
        if up2 then
          if val > (f - up2) and (up2 % 10 == 0) then
            str += roman_nums[idx + 2] + roman_nums[idx]
            subt = f - up2
            val -= subt
            divs = 0
          elsif val == (f - up2) and (f % 10 == 0) then
            # IX instead of VIV
            str += roman_nums[idx + 2] + roman_nums[idx]
            subt = f - up2
            val -= subt
            divs = 0
          end
        end
      end
      if divs > 3 then
        # IV instead of IIII
        str +=  roman_nums[idx] + roman_nums[idx-1]
      elsif divs > 0 
        str += roman_nums[idx] * divs 
      end
      val -= f * divs
    end
    str
  end
end
