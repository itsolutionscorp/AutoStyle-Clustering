class Hamming
  def compute(arg1,arg2)
    if arg1 == arg2
      0
    else
      count = 0
      arg1.chars.each_with_index do |c, i|
        if c != arg2[i]
          count += 1
        end
      end
      count
    end
  end
end
