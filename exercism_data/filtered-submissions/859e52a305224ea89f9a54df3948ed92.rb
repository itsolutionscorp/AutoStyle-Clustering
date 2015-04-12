class Hamming
  def compute(arg1, arg2)
   hammer = 0
   arg2.chars.each_with_index do |c, i|
     if arg1[i] != c
       hammer += 1
     end
   end
   hammer
  end
end
