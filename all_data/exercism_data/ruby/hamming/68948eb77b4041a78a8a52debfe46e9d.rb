class Hamming

  def self.compute(aString, bString)
    shorter_string, longer_string = (aString.size < bString.size) ? [aString, bString] : [bString, aString]
    trimmed = longer_string[0..shorter_string.size - 1]
    shorter_string.chars.zip(trimmed.chars).inject(0){ |acc, arr| arr[0]==arr[1] ? acc : acc + 1 }
  end

end
