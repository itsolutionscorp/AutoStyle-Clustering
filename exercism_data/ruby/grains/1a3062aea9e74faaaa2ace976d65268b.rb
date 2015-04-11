class Grains
  def square(index, prev_num = nil)
    if prev_num.nil?
      square(index - 1, 1)
    else
      if index > 0
        square(index - 1, prev_num * 2)
      else
        return prev_num
      end
    end
  end

  def total
    square(65) - 1
  end
end
