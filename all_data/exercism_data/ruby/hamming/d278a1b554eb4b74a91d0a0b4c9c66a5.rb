class Hamming
  def self.compute(arg1, arg2)
    x = 0
    arg1.chars.each_index {|i|
      x += 1 if arg1.chars[i] != arg2.chars[i]
    }
    x
  end
end
