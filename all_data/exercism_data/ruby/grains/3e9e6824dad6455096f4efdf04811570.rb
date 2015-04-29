class Grains
  def initialize
  end

  def square(num)
    store = [1]
    num.times { |i| store << store.last * 2 }
    store[num - 1]
  end

  def total
    store = [1]
    63.times { |i| store << store.last * 2 }
    store.inject(:+)
  end
end
