class Grains
  def square(num)
    # 2^n for n = 0..63
    1 << (num - 1)
  end

  def total(squares = 64)
    # Sigma(2^n) = 2^(n+1) - 1
    square(squares + 1) - 1
  end
end
