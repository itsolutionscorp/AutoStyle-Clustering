class Fixnum
  def to_roman
    case self
    when 0          then ""

    when 1..3       then "I"  * self

    when 4          then "IV"
    when 5..8       then "V"  + (self - 5).to_roman
    when 9          then "IX"
    when 10..39     then ("X" * (self / 10)) + (self % 10).to_roman

    when 40..49     then "XL" + (self - 40).to_roman
    when 50..89     then "L"  + (self - 50).to_roman
    when 90..99     then "XC" + (self - 90).to_roman
    when 100..399   then ("C" * (self / 100)) + (self % 100).to_roman

    when 400..499   then "CD" + (self - 400).to_roman
    when 500..899   then "D"  + (self - 500).to_roman
    when 900..999   then "CM" + (self - 900).to_roman
    when 1000..3999 then ("M" * (self / 1000)) + (self % 1000).to_roman
    end
  end
end
