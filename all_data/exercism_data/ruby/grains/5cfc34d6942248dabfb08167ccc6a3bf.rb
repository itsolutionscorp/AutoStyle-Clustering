class Grains
  TOTAL_SQUARES = 64

  def square(num)
    # 2^n for n = 0..63
    1 << (num - 1)
  end

  def total
    # Sigma(2^n) = 2^(n+1) - 1
    square(TOTAL_SQUARES + 1) - 1
  end
end
