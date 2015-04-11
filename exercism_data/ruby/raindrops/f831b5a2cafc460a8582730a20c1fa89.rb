class Raindrops
  def convert(n)
    drop = pling(n) + plang(n) + plong(n)
    drop.empty? ? n.to_s : drop.join
  end

  private

  def pling(n)
    text_for_factor "Pling", 3, n
  end

  def plang(n)
    text_for_factor "Plang", 5, n
  end

  def plong(n)
    text_for_factor "Plong", 7, n
  end

  def text_for_factor(text, factor, n)
    [*(text if n % factor == 0)]
  end
end
