class Fixnum
  CONVERSION = [
    ["M"  , 1000],
    ["CM" , 900],
    ["D"  , 500],
    ["CD" , 400],
    ["C"  , 100],
    ["XC" ,  90],
    ["L"  ,  50],
    ["XL" ,  40],
    ["X"  ,  10],
    ["IX" ,   9],
    ["V"  ,   5],
    ["IV" ,   4],
    ["I"  ,   1]
  ]
  def to_roman
    convert = self
    letters = ''
    CONVERSION.each do |arr| 
      while convert >= arr.last
        convert -= arr.last
        letters += arr.first
      end
    end
    letters
  end    
end
