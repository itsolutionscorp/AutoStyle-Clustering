class Grains

  def square(sq)
    1 << (sq - 1)
  end

  def total
    ("1" * 64).to_i(2)
  end

end
