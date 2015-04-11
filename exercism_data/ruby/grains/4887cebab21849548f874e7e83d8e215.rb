class Grains
  TOTAL_NUMBER_OF_SQUARES = 64
  @@squares = []

  def square(i)
    return 1 if i == 1
    if @@squares[i].nil?
      @@squares[i] = 2 * square(i-1)
    end
    @@squares[i]
  end

  def total
    (1..TOTAL_NUMBER_OF_SQUARES).inject(0) do |memo, i|
      memo += square(i)
    end
  end
end
