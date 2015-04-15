class Grains
  def square(n)
    2**(n-1)
  end

  def total(n=64)
    # Sum of a geometric series S, with n terms, and ratio r:
    # S = (first term)(1-r^n)/(1-r)  [in this case, r = 2]
    (2**n)-1
  end
end
