class Grains
  def square(n)
    2**(n-1)
  end

  def total
    #Faulhaber's formula
    m = 64

    (m**3)/3 + (m**2)/2 + m/6
  end
end
