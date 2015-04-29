class Hamming
  def self.compute(string1, string2)
    check_length string1, string2
    counter = 0
    (0..string1.length).each do |i|
      counter = counter + 1 if string1[i] != string2[i]
    end
    counter
  end

  def self.check_length(string1, string2)
    if string1.length != string2.length
      raise 'Strings are not of equal length.'
    end
  end
end
