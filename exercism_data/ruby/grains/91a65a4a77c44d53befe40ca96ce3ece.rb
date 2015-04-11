# exercise - ruby/grains
class Grains
  NUM_SQUARES = 64

  def square(n)
    2**(n - 1)
  end

  def total
    # lmao if you https://gist.github.com/wwest4/269a4f6adc5aa76949fd
    2**NUM_SQUARES - 1
  end

  def squares
    Array.new(NUM_SQUARES) do |x| 
      2**x
    end
  end
end
