class String
  def each_char_with_index
    index = 0
    self.each_char do |char|
      yield(char,index)
      index += 1
    end
  end
end

class Hamming
  def self.compute(base, desc)
    rtot = 0
    base.each_char_with_index do |c,i|
      rtot += 1 unless c == desc[i]
    end
    rtot
  end
end
