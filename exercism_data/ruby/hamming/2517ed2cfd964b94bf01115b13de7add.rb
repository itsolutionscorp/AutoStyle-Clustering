class Hamming
  def self.compute(arg_a, arg_b)
    difference_acum = 0
    for i in 0 .. arg_a.length
      if arg_a[i] != arg_b[i]
        difference_acum +=1 unless arg_a[i]==arg_b[i]
      end
    end
    difference_acum
  end
end
