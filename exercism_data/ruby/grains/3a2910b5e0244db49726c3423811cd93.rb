class Grains

  def square (n)
    2**(n-1)
  end

  # In case someone needs the total up to
  # any particular square, we use a default
  # parameter.
  def total (n=64)
    (2**n)-1
  end

end
