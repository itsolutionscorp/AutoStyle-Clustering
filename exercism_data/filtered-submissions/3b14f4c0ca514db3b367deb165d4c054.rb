class Hamming
  def compute(string1, string2)
    mismatch = 0
    my_len = [string1.length,string2.length].min
    my_len.times do |index|
      mismatch = mismatch + 1 if string1[index] != string2[index]
    end
    mismatch
  end
end
