class Raindrops
  def convert(n)
    result = "#{pling(n)}#{plang(n)}#{plong(n)}"
    return n.to_s if result == ''
    result
  end

  private

  def pling(n)
    return 'Pling' if n % 3 == 0
  end

  def plang(n)
    return 'Plang' if n % 5 == 0
  end

  def plong(n)
    return 'Plong' if n % 7 == 0
  end
end
