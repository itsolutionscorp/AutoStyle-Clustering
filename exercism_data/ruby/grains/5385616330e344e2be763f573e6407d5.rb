class Grains
  def initialize
    @memo = {}
  end

  def memo(n)
    if @memo.has_key?(n)
      @memo[n]
    else
      nil
    end
  end

  def memo_save(n, m)
    @memo[n] = m
    m
  end

  def square(n)
    if memo(n)
      memo(n)
    else
      memo_save(n, 2 ** (n-1))
    end
  end

  def total
    total_1
  end

  def total_0
    t = 0
    (1..64).each do |number|
      t += square(number)
    end
    t
  end

  def total_1
    (1..64).inject(0) do |memo, num|
      memo += square(num)
    end
  end
end
