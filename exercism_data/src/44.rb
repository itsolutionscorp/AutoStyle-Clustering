def compute(aString, bString)
    aString.chars.zip(bString.chars).count{ |arr| !arr.include?(nil) && arr[0]!=arr[1] }
  end