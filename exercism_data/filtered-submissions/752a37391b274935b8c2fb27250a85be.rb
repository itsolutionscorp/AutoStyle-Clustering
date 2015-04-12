class Hamming
  def compute(a,b)
    errors = 0

    min_length = [a.length,b.length].min

    for i in 0..min_length - 1
      errors +=1 if a[i] != b[i]
    end

    errors

  end
end
