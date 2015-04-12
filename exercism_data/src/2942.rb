class Hamming
  def compute(arg1,arg2)
    count = 0
    arg1.chars.each_with_index do |c, i|
      count += 1 if c != arg2[i]
    end
    count
  end
end
