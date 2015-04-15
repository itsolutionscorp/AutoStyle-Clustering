$cache = [1]

class Grains
  def square num
    index = num-1

    if $cache.count >= num
        $cache[index]
    else
        $cache[index] = square(num-1)*2
    end
  end

  def total
    $cache.reduce(&:+)
  end
end
