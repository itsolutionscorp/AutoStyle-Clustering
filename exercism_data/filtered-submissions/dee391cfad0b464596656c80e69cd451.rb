class Hamming

  def compute(string1, string2)
    hamming = 0
    [string1.length, string2.length].min.times do |l|
      hamming += 1 if string1[l] != string2[l]
    end
    hamming
  end
end
