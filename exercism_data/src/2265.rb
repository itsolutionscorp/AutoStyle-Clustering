class Hamming
  def compute(arg1, arg2)
    a = arg1.split(%r{\s*})
    b = arg2.split(%r{\s*})
    counter = a.count
    i = 0
    hamm = 0
    while i <= counter
      if a[i] != b[i]
        hamm += 1
      else
        hamm
      end
      i += 1
    end
    hamm
  end
end
