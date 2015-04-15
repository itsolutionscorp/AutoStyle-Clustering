class Fixnum
  def to_roman
    case
    when self < 4    then  'I' * self
    when self < 5    then 'IV'
    when self < 9    then  'V'                + (self -    5).to_roman
    when self < 10   then 'IX'
    when self < 40   then  'X' * (self / 10)  + (self %   10).to_roman
    when self < 50   then 'XL'                + (self %   10).to_roman
    when self < 90   then  'L'                + (self %   50).to_roman
    when self < 100  then 'XC'                + (self %   90).to_roman
    when self < 400  then  'C' * (self / 100) + (self %  100).to_roman
    when self < 500  then 'CD'                + (self %  400).to_roman
    when self < 900  then  'D'                + (self %  500).to_roman
    when self < 1000 then 'CM'                + (self %  900).to_roman
    else                   'M'                + (self - 1000).to_roman
    end
  end
end
