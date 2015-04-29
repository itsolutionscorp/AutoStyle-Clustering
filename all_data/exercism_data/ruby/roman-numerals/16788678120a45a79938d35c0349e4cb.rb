class Fixnum
  def to_roman
    self.to_s.chars.reverse.each_with_index.map do |digit, i|
      next if digit.to_i.zero?
      roman = case digit.to_i
      when 1 then "I"
      when 2 then "II"
      when 3 then "III"
      when 4 then "IV"
      when 5 then "V"
      when 6 then "VI"
      when 7 then "VII"
      when 8 then "VIII"
      when 9 then "IX"
      when 10 then "X"
      end

      case i
      when 0 then roman
      when 1 then roman.tr("IVX", "XLC")
      when 2 then roman.tr("IVX", "CDM")
      when 3 then roman.tr("I", "M")
      end
    end.reverse.join
  end
end
