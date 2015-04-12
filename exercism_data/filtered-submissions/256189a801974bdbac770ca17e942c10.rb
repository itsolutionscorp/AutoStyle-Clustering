class Hamming

  def compute(aString, bString)
    aString.chars.zip(bString.chars).take_while{ |arr| !arr.include? nil}.count{ |arr| arr[0]!=arr[1] }
  end

end
