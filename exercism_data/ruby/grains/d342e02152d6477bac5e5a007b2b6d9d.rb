class Grains
  def square(int)
    get_square(int)
  end

  def get_square(int, total = 1)
    if int == 1
      total
    else
      get_square(int -= 1, total * 2)
    end
  end

  def total
    1.upto(64).inject { |mem, i| mem += get_square(i) }
  end
end
