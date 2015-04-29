class Hamming

  def self.compute(aString, bString)
    aString.chars.zip(bString.chars).count{ |arr| if arr.include?(nil) then break else arr[0]!=arr[1] end } || 1
  end

end
